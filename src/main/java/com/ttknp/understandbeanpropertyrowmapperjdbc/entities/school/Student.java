package com.ttknp.understandbeanpropertyrowmapperjdbc.entities.school;

import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;


// @Table(name = "STUDENTS",schema = "TTKNP_SCHOOL")
public class Student {
    private Long id;
    private String fullName;
    private Date birthday;
    private String level;

    public Student() {
    }

    public Student(Long id, String fullName, Date birthday, String level) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("id=").append(id);
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", level=").append(level);
        sb.append('}');
        return sb.toString();
    }
}
