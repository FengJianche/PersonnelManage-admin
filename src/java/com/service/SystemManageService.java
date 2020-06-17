package com.service;

import com.entity.Department;
import com.entity.Department_Post;
import com.entity.Post;
import com.entity.StaffInfo;

import java.text.ParseException;
import java.util.List;

public interface SystemManageService {

    public List<Department_Post> allDepartmentPost();

    //获取职位下的员工
    public List<StaffInfo> getPostStaff(String postName);

    //更新职位信息
    public boolean upPostInfo(Post post,String old_post,String old_department,String new_department_name);

    //获取部门信息
    public List<Department> departmentInfo();

    //添加部门
    public boolean addDepartment(String name);
    //添加职位
    public boolean addPost(String name,String department_id);

    //删除部门
    public boolean delDepartment(String department_id);

    public boolean delPost(String post);

}
