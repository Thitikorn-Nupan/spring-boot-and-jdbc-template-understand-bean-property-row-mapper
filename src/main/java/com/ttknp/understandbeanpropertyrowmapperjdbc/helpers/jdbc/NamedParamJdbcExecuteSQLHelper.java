package com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

@Service
public class NamedParamJdbcExecuteSQLHelper {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate; // ** Perfect for auto

    @Autowired
    public NamedParamJdbcExecuteSQLHelper(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    // BeanPropertySqlParameterSource: It obtains parameter values from our domain/pojo/entity object, given that the object has proper getters and setters
    // (Per JavaBean specifications). Also in our sql query placeholder names should be same as our object variable names.
    public <T> Integer save(String sql,T object) {
        // SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(object);
        // can reduce
        return namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(object));
    }

    public <T> Integer countBy(String sql,T object){
        return namedParameterJdbcTemplate.queryForObject(sql,new BeanPropertySqlParameterSource(object),Integer.class);
    }


}
