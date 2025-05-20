package com.ttknp.understandbeanpropertyrowmapperjdbc.services.school;

import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.school.Teacher;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.jdbc.JdbcExecuteSQLHelper;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.sql_commands.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherService {

    private final JdbcExecuteSQLHelper jdbcExecuteSQLHelper;

    @Autowired
    public TeacherService(JdbcExecuteSQLHelper jdbcExecuteSQLHelper) {
        this.jdbcExecuteSQLHelper = jdbcExecuteSQLHelper;
    }

    public List<Teacher> findAll() {
        return jdbcExecuteSQLHelper.selectAllMapPropByBeanPropertyRowMapper(Commands.TEACHER_SELECT_ALL, Teacher.class);
    }

    public List<String> findAllFullName() {
        return jdbcExecuteSQLHelper.selectAllOnlyColumnMapPropByRowMapper(Commands.TEACHER_SELECT_ALL_ONLY_FULL_NAME,String.class);
    }

    public List<Long> findAllTid() {
        return jdbcExecuteSQLHelper.selectAllOnlyColumnMapPropByRowMapper(Commands.TEACHER_SELECT_ALL_ONLY_TID,Long.class);
    }

    public List<Map<String,Object>> findAll(String ...whateverParamsIJustWantTheSameMethodNameAsFindAll) {
        return jdbcExecuteSQLHelper.selectAllOnlyColumnMapPropByRowMapper(Commands.TEACHER_SELECT_ALL);
    }


    public Teacher findByPrimaryKey(Long id) {
        return jdbcExecuteSQLHelper.selectOneMapPropByBeanPropertyRowMapper(Commands.TEACHER_SELECT_ONE_BY_PK,Teacher.class,id);
    }

    public Map<String,String> findByFullNameAndPrimaryKey(String fullName, Long id) {
        return jdbcExecuteSQLHelper.selectOneMapPropByRowMapper(Commands.TEACHER_SELECT_ONE_BY_FULL_NAME_AND_PK,fullName,id);
    }

    public Teacher findByFullNameAndLevel(String fullName, String classId) {
        return jdbcExecuteSQLHelper.selectOneMapPropByBeanPropertyRowMapper(Commands.TEACHER_SELECT_ONE_BY_FULL_NAME_AND_CLASS_ID,Teacher.class,fullName,classId);
    }

}
