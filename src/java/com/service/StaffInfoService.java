package com.service;

import com.entity.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface StaffInfoService {
    //查询员工信息，只包括正式员工
    public List<StaffInfo> allStaffInfo();

    //返回员工信息至模态框
    public StaffInfo getOneStaffInfo(String id);

    //修改员工基本信息
    public boolean UpdateStaffInfo(String id, String name , String sex, String idnumber, String address, String worktime) throws ParseException;

    //返回查询员工的档案
    public StaffInfo_Files getOneStaffFiles(String id);

    //新增员工档案
    public boolean addStaffFiles(StaffFiles staffFiles);

    //高级搜索功能
    public List<StaffInfo> searchStaffInfo(String id,String name,String department,String post, String sex,String staff_status);

    //获取所有部门名称
    public List<String> getAllDepartmentName();
    //获取所有岗位名称
    public List<String> getAllPostName();

    //获取所有部门信息
    public List<Department> getAllDepartmentInfo();

    //获取部门下的岗位
    public List<Post> getAllPostInfo(String department_id);

    //录入员工信息 封装客户端数据
    public boolean addStaffInfo(StaffInfo staffInfo,String probation) throws ParseException;

    //获取试用期员工信息
    public List<StaffInfo_Probation> allProbation();




}
