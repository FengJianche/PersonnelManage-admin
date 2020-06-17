package com.dao;

import com.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProbationManageDao {

    //更新试用期时间
    public boolean upProbationEndtime(String id);

    //更新就职状态
    public boolean upStaffStatus(@Param("id") String id,@Param("staff_status") String staff_status);

    //更新试用期时间
    public boolean upProbationInfo(@Param("staff_id") String staff_id, @Param("begintime") String begintime,@Param("endtime") String endtime);

    //删除某个员工的试用
    public boolean delProbation(String id);

}
