package com.ttknp.understandbeanpropertyrowmapperjdbc.entities.school;

import java.util.Date;

public class Teacher {
    private Long tid;
    private String fullName;
    private Date birthday;
    private String classId;

    public Teacher(Long tid, String fullName, Date birthday, String classId) {
        this.tid = tid;
        this.fullName = fullName;
        this.birthday = birthday;
        this.classId = classId;
    }

    public Teacher() {
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Teacher{");
        sb.append("tid=").append(tid);
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", classId=").append(classId);
        sb.append('}');
        return sb.toString();
    }
}
