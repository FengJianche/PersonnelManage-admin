package com.entity;

import java.util.Date;

public class StaffInfo_TransferRecord {
    public String id;
    public String name;
    public String sex;
    public Date worktime;
    public String staff_status;
    public String old_department;
    public String old_post;
    public String new_department;
    public String new_post;
    public Date optiontime;
    public String admin_id;
    public String reason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getWorktime() {
        return worktime;
    }

    public void setWorktime(Date worktime) {
        this.worktime = worktime;
    }

    public String getStaff_status() {
        return staff_status;
    }

    public void setStaff_status(String staff_status) {
        this.staff_status = staff_status;
    }

    public String getOld_department() {
        return old_department;
    }

    public void setOld_department(String old_department) {
        this.old_department = old_department;
    }

    public String getOld_post() {
        return old_post;
    }

    public void setOld_post(String old_post) {
        this.old_post = old_post;
    }

    public String getNew_department() {
        return new_department;
    }

    public void setNew_department(String new_department) {
        this.new_department = new_department;
    }

    public String getNew_post() {
        return new_post;
    }

    public void setNew_post(String new_post) {
        this.new_post = new_post;
    }

    public Date getOptiontime() {
        return optiontime;
    }

    public void setOptiontime(Date optiontime) {
        this.optiontime = optiontime;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

