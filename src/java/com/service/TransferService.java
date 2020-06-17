package com.service;

import com.entity.Department;
import com.entity.StaffInfo;
import com.entity.TransferRecord;

import java.text.ParseException;
import java.util.List;

public interface TransferService {

    //查询员工信息，只包括正式员工
    public List<StaffInfo> allStaffInfo();
    //获取所有部门信息
    public List<Department> getAllDepartmentInfo();
    //检索功能
    public List<StaffInfo> searchStaffInfo(String id,String name,String department,String post, String sex,String staff_status);

    //人员调动
    public boolean StaffTransfer(String id,String old_department, String old_post,String new_department,String new_post,String admin_id,String reason);

    //查询调动记录
    public List<TransferRecord> allTransferRecord();

    //离职
    public boolean StaffDimission(String id,String old_department, String old_post,String dimission,String admin_id,String reason);

}
