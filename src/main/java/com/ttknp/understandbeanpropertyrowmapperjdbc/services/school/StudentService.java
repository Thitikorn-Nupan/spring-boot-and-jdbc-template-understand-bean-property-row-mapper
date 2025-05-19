package com.ttknp.understandbeanpropertyrowmapperjdbc.services.school;

import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.school.Student;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.jdbc.JdbcExecuteSQLHelper;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.jdbc.NamedParamJdbcExecuteSQLHelper;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.sql_commands.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    private final JdbcExecuteSQLHelper jdbcExecuteSQLHelper;
    private final NamedParamJdbcExecuteSQLHelper namedParamJdbcExecuteSQLHelper;


    @Autowired
    public StudentService(JdbcExecuteSQLHelper jdbcExecuteSQLHelper, NamedParamJdbcExecuteSQLHelper namedParamJdbcExecuteSQLHelper) {
        this.jdbcExecuteSQLHelper = jdbcExecuteSQLHelper;
        this.namedParamJdbcExecuteSQLHelper = namedParamJdbcExecuteSQLHelper;
    }

    public List<Student> findAll() {
        return jdbcExecuteSQLHelper.selectAllMapPropByBeanPropertyRowMapper(Commands.STUDENT_SELECT_ALL, Student.class);
    }

    public List<String> findAllFullName() {
        return jdbcExecuteSQLHelper.selectAllMapPropByRowMapper(Commands.STUDENT_SELECT_ALL_ONLY_FULL_NAME,String.class);
    }

    public List<Long> findAllSid() {
        return jdbcExecuteSQLHelper.selectAllMapPropByRowMapper(Commands.STUDENT_SELECT_ALL_ONLY_SID,Long.class);
    }

    public List<Map<String,Object>> findAll(String ...whateverParamsIJustWantTheSameMethodNameAsFindAll) {
        return jdbcExecuteSQLHelper.selectAllMapPropByRowMapper(Commands.STUDENT_SELECT_ALL);
    }

    public Student findByPrimaryKey(Long id) {
        return jdbcExecuteSQLHelper.selectOneMapPropByBeanPropertyRowMapper(Commands.STUDENT_SELECT_ONE_BY_PK,Student.class,id);
    }

    public Map<String,String> findByFullNameAndPrimaryKey(String fullName, Long id) {
        return jdbcExecuteSQLHelper.selectOneMapPropByRowMapper(Commands.STUDENT_SELECT_ONE_BY_FULL_NAME_AND_PK,fullName,id);
    }

    public Student findByFullNameAndLevel(String fullName, String level) {
        return jdbcExecuteSQLHelper.selectOneMapPropByBeanPropertyRowMapper(Commands.STUDENT_SELECT_ONE_BY_FULL_NAME_AND_LEVEL,Student.class,fullName,level);
    }

    public Integer save(Student student) {
        int rowAffect = namedParamJdbcExecuteSQLHelper.save(Commands.STUDENT_INSERT,student);
        return rowAffect;
    }

    public Integer countLevel(Student student) {
        int rowAffect = namedParamJdbcExecuteSQLHelper.countBy(Commands.STUDENT_SELECT_COUNT_LEVEL,student);
        return rowAffect;
    }

    public Integer deleteByPkOrFullName(Student student) {
        int rowAffect = 0;
        if (student.getSid() != null) {
            rowAffect = jdbcExecuteSQLHelper.deleteByUniq(Commands.STUDENT_DELETE_ONE_BY_PK,student.getSid());
        } else if (student.getFullName() != null) {
            rowAffect = jdbcExecuteSQLHelper.deleteByUniq(Commands.STUDENT_DELETE_ONE_BY_FULL_NAME,student.getFullName());
        }
        return rowAffect;
    }



}
