package com.dao;

import com.entity.Department;
import com.entity.StaffInfo;
import com.entity.TransferRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransferDao {

    //查询员工的详细信息
    public List<StaffInfo> allStaffInfo();

    //查询所有部门信息
    public List<Department> getAllDepartmentInfo();

    //高级模糊查询（可拓展）->员工查询
    public List<StaffInfo> searchStaffInfo(StaffInfo staffInfo);

    //添加调动信息
    public boolean addTransferInfo(TransferRecord transferRecord);

    //修改员工部门与职位信息
    public boolean upStaffDpPsInfo(String id,String department,String post);

    //查询所有调动记录
    public List<TransferRecord> allTransferRecord();

    public boolean upStaffStaffStatus(String id,String dimission);



}
