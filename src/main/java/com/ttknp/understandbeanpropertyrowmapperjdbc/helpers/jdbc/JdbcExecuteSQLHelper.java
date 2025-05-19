package com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdbcExecuteSQLHelper {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcExecuteSQLHelper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public <T> List<T> selectAllMapPropByBeanPropertyRowMapper(String sql, Class<T> aBeanClass) {
        // var rowMapper = BeanPropertyRowMapper.newInstance(aBeanClass); // auto map getter/setter
        // return jdbcTemplate.query(sql,rowMapper);
        // can reduce by below
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<T>(aBeanClass));
    }

    public <T> T selectOneMapPropByBeanPropertyRowMapper(String sql, Class<T> aBeanClass, Object... params) {
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<T>(aBeanClass),params);
    }



}
