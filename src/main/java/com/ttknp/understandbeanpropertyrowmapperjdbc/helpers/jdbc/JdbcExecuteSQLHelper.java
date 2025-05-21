package com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class JdbcExecuteSQLHelper {

    private static final Logger log = LoggerFactory.getLogger(JdbcExecuteSQLHelper.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcExecuteSQLHelper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // It's very useful for sql where result more 1 rows
    public <T> T selectAllWhereMapPropByRowMapper(String sql, ResultSetExtractor<T> resultSetExtractor,Object ...params) {
        // it maps ? auto
        return jdbcTemplate.query(sql, resultSetExtractor,params);
    }

    // BeanPropertyRowMapper, this class saves you a lot of time for the mapping.
    public <T> List<T> selectAllMapPropByBeanPropertyRowMapper(String sql, Class<T> aBeanClass) {
        // var rowMapper = BeanPropertyRowMapper.newInstance(aBeanClass); // auto map getter/setter
        // return jdbcTemplate.query(sql,rowMapper);
        // can reduce by below
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(aBeanClass));
    }

    // jdbcTemplate.queryForList, it works for rows, but not recommend, the mapping in Map may not same as the object, need casting.
    public List<Map<String, Object>> selectAllOnlyColumnMapPropByRowMapper(String sql) {
        return jdbcTemplate.queryForList(sql);
    }

    // U can be only String , Integer , ... anything but should not be Object
    public <U> List<U> selectAllOnlyColumnMapPropByRowMapper(String sql, Class<U> aClass) {
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

    // ResultSetExtractor works like RowMapper
    public <T> T selectMapPropByResultSetExtractor(String sql,ResultSetExtractor<T> resultSetExtractor){
        return jdbcTemplate.query(sql,resultSetExtractor) ;
    }

    // Executing multiple queries with execute(...) The method execute(String sql) returns void if a call of the method succeeds without errors.
    public Integer multipleQueries(String sql) {
        try {
            jdbcTemplate.execute(sql);
            return 1;
        }catch (Exception e){
            log.debug("Error multiple queries {}", e.getMessage());
            return -1;
        }
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
