package com.service;

import com.dao.ProbationManageDao;
import com.dao.StaffInfoDao;
import com.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service("ProbationManageService")
public class ProbationManageServiceImpl implements ProbationManageService {
    @Autowired
    private ProbationManageDao probationManageDao;


    @Override
    public boolean probationReturn(String id,String staff_status) throws ParseException {
        //更新结束试用时间
        probationManageDao.upProbationEndtime(id);
        return probationManageDao.upStaffStatus(id,staff_status);
    }

    @Override
    public boolean upProbationInfo(String staff_id,String begintime, String endtime) {
        return probationManageDao.upProbationInfo(staff_id,begintime,endtime);
    }

    @Override
    public boolean refuseProbation(String id) {
        probationManageDao.delProbation(id);
        probationManageDao.upStaffStatus(id,"辞退");
        return true;
    }
}
