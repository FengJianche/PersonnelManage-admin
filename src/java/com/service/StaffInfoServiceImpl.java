package com.service;

import com.dao.StaffInfoDao;
import com.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service("StaffInfoService")
public class StaffInfoServiceImpl implements StaffInfoService {
    @Autowired
    private StaffInfoDao staffInfoDao;

    @Override
    public List<StaffInfo> allStaffInfo() {
        return staffInfoDao.allStaffInfo();
    }

    @Override
    public StaffInfo getOneStaffInfo(String id) {
        return staffInfoDao.getOneStaffInfo(id);
    }

    @Override
    public boolean UpdateStaffInfo(String id, String name, String sex, String idnumber, String address, String worktime) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date worktime_new = simpleDateFormat.parse(worktime);
        //封装成实体类
        StaffInfo staffInfo = new StaffInfo();
        staffInfo.setId(id);
        staffInfo.setName(name);
        staffInfo.setSex(sex);
        staffInfo.setIdnumber(idnumber);
        staffInfo.setAddress(address);
        staffInfo.setWorktime(worktime_new);

//        身份证唯一性判断
        List<StaffInfo> staffInfos = staffInfoDao.idNumberIfExist(staffInfo);
        if(staffInfos.size()<=1)
            return staffInfoDao.UpdateStaffInfo(staffInfo);
        else
            return false;
    }

    @Override
    public StaffInfo_Files getOneStaffFiles(String id) {
        return staffInfoDao .getOneStaffFiles(id);
    }

    @Override
    public boolean addStaffFiles(StaffFiles staffFiles) {
        return staffInfoDao.addStaffFiles(staffFiles);
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
        return staffInfoDao.searchStaffInfo(staffInfo);
    }

    @Override
    public List<String> getAllDepartmentName() {
        return staffInfoDao.getAllDepartmentName();
    }

    @Override
    public List<String> getAllPostName() {
        return staffInfoDao.getAllPostName();
    }

    @Override
    public List<Department> getAllDepartmentInfo() {
        return staffInfoDao.getAllDepartmentInfo();
    }

    @Override
    public List<Post> getAllPostInfo(String department_id) {
        return staffInfoDao.getAllPostInfo(department_id);
    }

    @Override
    public boolean addStaffInfo(StaffInfo staffInfo,String probation_old) throws ParseException {
        //身份证判断
        List<StaffInfo> staffInfos = staffInfoDao.idNumberIfExist(staffInfo);
        if(staffInfos.size()<=1){
            //封装staffInfo信息
            //获取当前系统时间
            Date nowdate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            String beginyear = sdf.format(nowdate);
            int endyear = Integer.parseInt(beginyear) + 1 ;
            //封装查询岗位下人数的数量所需的参数
            String begintime = beginyear + "-1-1";
            String endtime = endyear + "-1-1";
            System.out.println("当前年份：" + begintime);
            System.out.println("最大年份：" + endtime);
            int postPersonNumber = (staffInfoDao.getPostPersonNumber(staffInfo.post,begintime,endtime)) +1;
            System.out.println("该岗位下有: " + postPersonNumber);

            //封装员工编号
            String postPersonNumber_now = postPersonNumber + "";
            if(postPersonNumber_now.length()==2)
                postPersonNumber_now = "0"+ postPersonNumber_now;
            if(postPersonNumber_now.length()==1)
                postPersonNumber_now = "00" + postPersonNumber_now;
            staffInfo.setId(((sdf.format(nowdate)).substring(2))+staffInfo.getId()+postPersonNumber_now);
            System.out.println("封装后的getId:" + staffInfo.getId());
            System.out.println("封装后的getName:" + staffInfo.getName());
            System.out.println("封装后的getSex:" + staffInfo.getSex());
            System.out.println("封装后的getIdnumber:" + staffInfo.getIdnumber());
            System.out.println("封装后的getAddress:" + staffInfo.getAddress());
            System.out.println("封装后的getDepartment:" + staffInfo.getDepartment());
            System.out.println("封装后的getPost:" + staffInfo.getPost());
            System.out.println("封装后的getTechpost:" + staffInfo.getTechpost());
            staffInfoDao.addStaffInfo(staffInfo);

            //试用期员工录入
            if(staffInfo.getStaff_status().equals("试用期")){
                //封装
                String[] probationTime = probation_old.split(" - ");
                Probation probation = new Probation();
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
                probation.setStaff_id(staffInfo.getId());
                probation.setBegintime(sdf1.parse(probationTime[0]));
                probation.setEndtime(sdf1.parse(probationTime[1]));
                //保存至数据库
                staffInfoDao.addProbationStaff(probation);
            }
            return true;
        }
        else
            return false;

    }

    @Override
    public List<StaffInfo_Probation> allProbation() {
        return staffInfoDao.allprobation();
    }


}
