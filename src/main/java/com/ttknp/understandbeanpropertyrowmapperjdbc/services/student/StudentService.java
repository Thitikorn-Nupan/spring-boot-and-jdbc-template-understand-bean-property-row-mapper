package com.ttknp.understandbeanpropertyrowmapperjdbc.services.student;

import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.school.Student;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.jdbc.JdbcExecuteSQLHelper;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.sql_commands.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final JdbcExecuteSQLHelper jdbcExecuteSQLHelper;

    @Autowired
    public StudentService(JdbcExecuteSQLHelper jdbcExecuteSQLHelper) {
        this.jdbcExecuteSQLHelper = jdbcExecuteSQLHelper;
    }

    public List<Student> findAll() {
        return jdbcExecuteSQLHelper.selectAllMapPropByBeanPropertyRowMapper(Commands.STUDENT_SELECT_ALL, Student.class);
    }

}
