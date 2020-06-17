package com.dao;

import com.entity.*;

import java.util.List;

public interface StaffInfoDao {
    //查询员工的详细信息
    public List<StaffInfo> allStaffInfo();

    //根据员工id查询员工信息
    public StaffInfo getOneStaffInfo(String id);

    //根据员工id 修改员工基础信息
    public boolean UpdateStaffInfo(StaffInfo staffinfo);

    //根据员工id 查询身份证是否重复
    public List<StaffInfo> idNumberIfExist(StaffInfo staffInfo);

    //根据员工id 查询员工档案信息
    public StaffInfo_Files getOneStaffFiles(String id);

    //根据员工id 增加员工档案
    public boolean addStaffFiles(StaffFiles staffFiles);

    //根据员工di 删除员工档案
    public boolean delStaffFiles(StaffFiles staffFiles);

    //高级模糊查询（可拓展）->员工查询
    public List<StaffInfo> searchStaffInfo(StaffInfo staffInfo);

    //查询所有部门名称
    public List<String> getAllDepartmentName();

    //查询所有岗位名称
    public List<String> getAllPostName();

    //查询所有部门信息
    public List<Department> getAllDepartmentInfo();

    //根据部门id查询部门下的岗位
    public List<Post> getAllPostInfo(String department_id);

    //添加员工信息
    public boolean addStaffInfo(StaffInfo staffInfo);

    //获取当前年份下 指定岗位下的员工数量
    public int getPostPersonNumber(String post_name,String begintime,String endtime);

    //添加试用期员工
    public boolean addProbationStaff(Probation probation);

    //查询所有试用期员工
    public List<StaffInfo_Probation> allprobation();


}
