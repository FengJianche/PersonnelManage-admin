package com.service;

import com.dao.TransferDao;
import com.entity.Department;
import com.entity.StaffInfo;
import com.entity.TransferRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Transactional
@Service("TransferService")
public class TransferServiceImpl implements TransferService {
    @Autowired
    private TransferDao transferDao;


    @Override
    public List<StaffInfo> allStaffInfo() {
        return transferDao.allStaffInfo();
    }

    @Override
    public List<Department> getAllDepartmentInfo() {
        return transferDao.getAllDepartmentInfo();
    }

    @Override
    public List<StaffInfo> searchStaffInfo(String id, String name, String department, String post, String sex, String staff_status) {
        System.out.println("接收客户端传回的查询参数");
        System.out.println(id);
        System.out.println(name);
        System.out.println(department);
        System.out.println(post);
        System.out.println(sex);
        System.out.println(staff_status);
        //封装
        //去除空格
        id = id.trim();
        name = name.trim();
        if(!id.equals(""))
            id = "%" + id +"%" ;
        if(!name.equals(""))
            name = "%" + name +"%" ;

        //封装多选框
        //部门
        if(!department.equals("all")){
            department = department.replace("-","\',\'");
            department = department.substring(3);
            department = "'" + department + "'";
        }
        //岗位
        if(!post.equals("all")){
            post = post.replace("-","\',\'");
            post = post.substring(3);
            post = "'" + post + "'";

        }
        System.out.println("处理过后的参数");

        System.out.println(id);
        System.out.println(name);
        System.out.println(department);
        System.out.println(post);
        System.out.println(sex);
        System.out.println(staff_status);
        //封装实体类
        StaffInfo staffInfo = new StaffInfo();
        staffInfo.setId(id);
        staffInfo.setName(name);
        staffInfo.setDepartment(department);
        staffInfo.setPost(post);
        staffInfo.setSex(sex);
        staffInfo.setStaff_status(staff_status);

        //调用持久层
        return transferDao.searchStaffInfo(staffInfo);
    }

    @Override
    public boolean StaffTransfer(String id,String old_department, String old_post,String new_department,String new_post,String admin_id,String reason) {
        //更新人员调动信息
        String[] len = id.split("@");
        String[] old_department_len = old_department.split("@");
        String[] old_post_len = old_post.split("@");

        for(int i=0; i < len.length ; i++){
            System.out.println("正在更新: "+len[i] +"的职位信息");
            transferDao.upStaffDpPsInfo(len[i],new_department,new_post);
            //封装调动记录信息
            TransferRecord transferRecord = new TransferRecord();
            transferRecord.setStaff_id(len[i]);
            transferRecord.setAdmin_id(admin_id);
            transferRecord.setNew_department(new_department);
            transferRecord.setNew_post(new_post);
            transferRecord.setOld_department(old_department_len[i]);
            transferRecord.setOld_post(old_post_len[i]);
            transferRecord.setReason(reason);
            //添加调动记录
            transferDao.addTransferInfo(transferRecord);
        }

        return true;
    }

    @Override
    public List<TransferRecord> allTransferRecord() {
        return transferDao.allTransferRecord();
    }

    @Override
    public boolean StaffDimission(String id, String old_department, String old_post, String dimission, String admin_id, String reason) {
        //更新人员调动信息
        String[] len = id.split("@");
        String[] old_department_len = old_department.split("@");
        String[] old_post_len = old_post.split("@");

        for(int i=0; i < len.length ; i++){
            System.out.println("正在更新: "+len[i] +"的职位信息");
            transferDao.upStaffStaffStatus(len[i],dimission);
            //封装调动记录信息
            TransferRecord transferRecord = new TransferRecord();
            transferRecord.setStaff_id(len[i]);
            transferRecord.setAdmin_id(admin_id);
            transferRecord.setNew_department("离职");
            transferRecord.setNew_post("离职");
            transferRecord.setOld_department(old_department_len[i]);
            transferRecord.setOld_post(old_post_len[i]);
            transferRecord.setReason(reason);
            //添加调动记录
            transferDao.addTransferInfo(transferRecord);
        }

        return true;
    }
}
