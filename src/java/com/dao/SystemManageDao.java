package com.dao;

import com.entity.Department;
import com.entity.Department_Post;
import com.entity.Post;
import com.entity.StaffInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemManageDao {


    //获取职位数量
    public int getPostNumber();

    //获取部门数量
    public int getDepartmentNumber();

    //获取部门下的岗位信息
    public List<Department_Post> getALLDepartment_Post();

    //获取某岗位下的员工,通过岗位名称
    public List<StaffInfo> getStaffInPostInfo(String postName);

    //删除某岗位(通过岗位id)
    public boolean deletePostInfo(String name);

    //修改某岗位信息
    public boolean upPostInfo(String id,String name,String department_id,String old_post_name);

    //修改某部门信息
    public boolean upDepartementInfo(Department department);

    //更新员工岗位信息
    public boolean upStaffPostInfo(String new_post,String old_post,String new_department_name);

    //获取部门信息
    public List<Department> getDepartmentInfo();
    //获取某岗位下的员工id
    public List<String> getStaffInPostStaffId(String post);

    //添加部门
    public boolean addDepartment(String id,String name,String reason);

    public boolean addPost(String id,String name,String department_id,String reason);

    //获取某部门下的岗位
    public List<Department_Post> postInDepartment(String department_id);

    //删除部门
    public  boolean delDepartment(String department_id);


}
