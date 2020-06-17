package com.service;

import com.dao.ProbationManageDao;
import com.dao.SystemManageDao;
import com.dao.TransferDao;
import com.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;

@Transactional
@Service("SystemManageService")
public class SystemManageServiceImpl implements SystemManageService {
    @Autowired
    private SystemManageDao systemManageDao;

    @Autowired
    private TransferDao transferDao;

    @Override
    public List<Department_Post> allDepartmentPost() {
        return systemManageDao.getALLDepartment_Post();
    }

    @Override
    public List<StaffInfo> getPostStaff(String postName) {
        return systemManageDao.getStaffInPostInfo(postName);
    }

    @Override
    public boolean upPostInfo(Post post,String old_post,String old_department,String new_department_name) {
        //1.更新职位信息
        systemManageDao.upPostInfo(post.getId(),post.getName(),post.getDepartment_id(),old_post);
        //2.获取需要更新的员工id
        List<String> upStaff_id=systemManageDao.getStaffInPostStaffId(old_post);
        //3.员工调动记录
        //封装
        int flag =0;//防止同时间插入记录导致主键重复
        for(String staff_id : upStaff_id ){
            TransferRecord transferRecord = new TransferRecord();
            transferRecord.setStaff_id(staff_id);
            transferRecord.setOld_department(old_department);
            transferRecord.setOld_post(old_post);
            transferRecord.setNew_department(new_department_name);
            transferRecord.setNew_post(post.getName());
            transferRecord.setAdmin_id("系统监控管理中心"+flag);
            transferRecord.setReason("由于职位转移、合并、整合入其他部门");
            transferDao.addTransferInfo(transferRecord);
            flag++;
        }
        //4.更新员工信息
        return systemManageDao.upStaffPostInfo(post.getName(),old_post,new_department_name);

    }

    @Override
    public List<Department> departmentInfo() {
        return systemManageDao.getDepartmentInfo();
    }

    @Override
    public boolean addDepartment(String name) {
        //计算部门id
        String departmentNumber =String.valueOf((systemManageDao.getDepartmentNumber()+1));
        if(departmentNumber.length()<2){
            departmentNumber = "0"+departmentNumber;
        }
        //添加部门
        return systemManageDao.addDepartment(departmentNumber,name,"管理员通过手动新增部门");
    }

    @Override
    public boolean addPost(String name, String department_id) {
        //计算职位id
        String postNumber =String.valueOf((systemManageDao.getPostNumber()+1));
        if(postNumber.length()<2){
            postNumber = "0"+postNumber;
        }
        return systemManageDao.addPost(postNumber,name,department_id,"管理员通过手动新增职位");
    }

    @Override
    public boolean delDepartment(String department_id) {
        //查看该部门下是否含有职位
        List<Department_Post> department_posts = systemManageDao.postInDepartment(department_id);

        if(department_posts.size()==0){
            if (systemManageDao.delDepartment(department_id))
                return true;
        }
        return false;
    }

    @Override
    public boolean delPost(String post) {
        //查看该职位下是否有人
        List<String> staffInfos = systemManageDao.getStaffInPostStaffId(post);

        if(staffInfos.size()==0){
            if (systemManageDao.deletePostInfo(post))
                return true;
        }
        return false;
    }

}
