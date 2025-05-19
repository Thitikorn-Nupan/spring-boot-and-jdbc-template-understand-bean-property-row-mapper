package com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.jdbc;

import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.school.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class JdbcExecuteSQLHelper {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcExecuteSQLHelper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // BeanPropertyRowMapper, this class saves you a lot of time for the mapping.
    public <T> List<T> selectAllMapPropByBeanPropertyRowMapper(String sql, Class<T> aBeanClass) {
        // var rowMapper = BeanPropertyRowMapper.newInstance(aBeanClass); // auto map getter/setter
        // return jdbcTemplate.query(sql,rowMapper);
        // can reduce by below
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(aBeanClass));
    }

    // jdbcTemplate.queryForList, it works for rows, but not recommend, the mapping in Map may not same as the object, need casting.
    public List<Map<String, Object>> selectAllMapPropByRowMapper(String sql) {
        return jdbcTemplate.queryForList(sql);
    }

    // U can be only String , Integer , ... anything but should not be Object
    public <U> List<U> selectAllMapPropByRowMapper(String sql, Class<U> aClass) {
        return jdbcTemplate.queryForList(sql, aClass);
    }

    public <T> T selectOneMapPropByBeanPropertyRowMapper(String sql, Class<T> aBeanClass, Object... params) {
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<T>(aBeanClass), params);
    }

    public Map<String, String> selectOneMapPropByRowMapper(String sql, Object... params) {
        return this.jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> mapRow(resultSet), params); // result like this : {SID=1, FULL_NAME=Adam Slider, BIRTHDAY=1999-09-19, LEVEL=A+}
    }

    // <T extends Student> meaning i need the generic have to relation with Student class
    // U can be only String , Integer , ... anything but should not be Object
    public <U> Integer deleteByUniq(String sql, U param) {
        return jdbcTemplate.update(sql, param);
    }

    public  Integer countRows(String sql){
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    private Map<String, String> mapRow(ResultSet resultSet) throws SQLException {
        Map<String, String> rowMap = new LinkedHashMap<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnLabel(i);
            String columnValue = resultSet.getString(i);
            rowMap.put(columnName, columnValue);
        }
        return rowMap;
    }


}
