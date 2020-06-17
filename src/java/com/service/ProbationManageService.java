package com.service;

import com.entity.*;

import java.text.ParseException;
import java.util.List;

public interface ProbationManageService {

    //员工转正
    public boolean probationReturn(String id,String staff_status) throws ParseException;

    //试用期更新
    public boolean upProbationInfo(String staff_id,String begintime, String endtime);

    //试用期拒绝员工转正
    public boolean refuseProbation(String id);

}
