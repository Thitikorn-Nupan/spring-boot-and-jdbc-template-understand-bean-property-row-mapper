package com.ttknp.understandbeanpropertyrowmapperjdbc.services.school;

import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.school.Student;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.jdbc.JdbcExecuteSQLHelper;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.jdbc.NamedParamJdbcExecuteSQLHelper;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.sql_commands.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class StudentService {

    private final JdbcExecuteSQLHelper jdbcExecuteSQLHelper;
    private final NamedParamJdbcExecuteSQLHelper namedParamJdbcExecuteSQLHelper;


    @Autowired
    public StudentService(JdbcExecuteSQLHelper jdbcExecuteSQLHelper, NamedParamJdbcExecuteSQLHelper namedParamJdbcExecuteSQLHelper) {
        this.jdbcExecuteSQLHelper = jdbcExecuteSQLHelper;
        this.namedParamJdbcExecuteSQLHelper = namedParamJdbcExecuteSQLHelper;
    }

    public List<Student> findAllFilterLevel(String level) {
        return jdbcExecuteSQLHelper.selectWhereMapPropByRowMapper(Commands.STUDENT_SELECT_ALL_BY_LEVEL,new StudentListResultSetExtractor(),level);
    }

    public List<Student> findAll() {
        return jdbcExecuteSQLHelper.selectAllMapPropByBeanPropertyRowMapper(Commands.STUDENT_SELECT_ALL, Student.class);
    }

    public List<String> findAllFullName() {
        return jdbcExecuteSQLHelper.selectAllOnlyColumnMapPropByRowMapper(Commands.STUDENT_SELECT_ALL_ONLY_FULL_NAME,String.class);
    }

    public List<Object> findAllOnlyColumnName(String name) {
        return jdbcExecuteSQLHelper.selectAllOnlyColumnMapPropByRowMapper(Student.class,Object.class,name);
    }


    public List<Long> findAllSid() {
        return jdbcExecuteSQLHelper.selectAllOnlyColumnMapPropByRowMapper(Commands.STUDENT_SELECT_ALL_ONLY_SID,Long.class);
    }

    public List<Map<String,Object>> findAll(String ...whateverParamsIJustWantTheSameMethodNameAsFindAll) {
        return jdbcExecuteSQLHelper.selectAllOnlyColumnMapPropByRowMapper(Commands.STUDENT_SELECT_ALL);
    }

    public Student findByPrimaryKey(Long id) {
        return jdbcExecuteSQLHelper.selectOneMapPropByBeanPropertyRowMapper(Commands.STUDENT_SELECT_ONE_BY_PK,Student.class,id);
    }

    public Student findByUniqKey(String uniqKey,Long id) {
        return jdbcExecuteSQLHelper.selectOneMapPropByBeanPropertyRowMapper(Student.class,uniqKey,id);
    }

    public Student findByUniqKey(String uniqKey,String fullName) {
        return jdbcExecuteSQLHelper.selectOneMapPropByBeanPropertyRowMapper(Student.class,uniqKey,fullName);
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

    // Simple Jdbc Insert
    public Number save(MapSqlParameterSource params) {
        return jdbcExecuteSQLHelper.save("TTKNP_SCHOOL","STUDENTS","SID",params);
    }

    // Simple Jdbc Insert
    public Integer save(Student student,Object ...whateverParamsIJustWantTheSameMethodNameAsSave) {
        return jdbcExecuteSQLHelper.save("TTKNP_SCHOOL","STUDENTS","SID",student);
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

    public String findFullNameBySID(Long sid) {
        return jdbcExecuteSQLHelper.selectOneColumn(Student.class, String.class,"FULL_NAME","SID",sid);
    }

    public Date findBirthdayBySID(Long sid) {
        return jdbcExecuteSQLHelper.selectOneColumn(Student.class, Date.class,"BIRTHDAY","SID",sid);
    }

    private static class StudentListResultSetExtractor implements ResultSetExtractor<List<Student>> {
        @Override
        public List<Student> extractData(ResultSet rs) throws SQLException {
            List<Student> students = new ArrayList<>();
            while (rs.next()) {
                Long sid = rs.getLong("SID");
                String fullName = rs.getString("FULL_NAME");
                Date birthday = rs.getDate("BIRTHDAY");
                String level = rs.getString("level");
                students.add(new Student(sid,fullName,birthday,level));
            }
            return students;
        }
    }


}
