package com.ttknp.understandbeanpropertyrowmapperjdbc.services.shop;

import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.shop.Employee;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.jdbc.JdbcExecuteSQLHelper;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.jdbc.NamedParamJdbcExecuteSQLHelper;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.sql_commands.Commands;
import com.ttknp.understandbeanpropertyrowmapperjdbc.services.shop.common.ServiceCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService implements ServiceCommon<Employee> {

    private final JdbcExecuteSQLHelper jdbcExecuteSQLHelper;
    private final NamedParamJdbcExecuteSQLHelper namedParamJdbcExecuteSQLHelper;

    @Autowired
    public EmployeeService(JdbcExecuteSQLHelper jdbcExecuteSQLHelper, NamedParamJdbcExecuteSQLHelper namedParamJdbcExecuteSQLHelper) {
        this.jdbcExecuteSQLHelper = jdbcExecuteSQLHelper;
        this.namedParamJdbcExecuteSQLHelper = namedParamJdbcExecuteSQLHelper;
    }

    @Override
    public Integer save(Employee object) {
        return namedParamJdbcExecuteSQLHelper.save(Commands.EMPLOYEE_INSERT,object);
    }

    @Override
    public Integer countRows() {
        return jdbcExecuteSQLHelper.countRows(Commands.EMPLOYEE_SELECT_COUNT);
    }

    @Override
    public List<Employee> readsAsList() {
        return jdbcExecuteSQLHelper.selectAllMapPropByBeanPropertyRowMapper(Commands.EMPLOYEE_SELECT_ALL,Employee.class);
    }

    @Override
    public List<Employee> readsAsListSpecifyColumn() {
        StringBuilder sql = new StringBuilder(Commands.EMPLOYEE_SELECT_ALL_FIRSTNAME_START_WITH);
        sql.append(" 'A%'"); // append as concast string like +
        return jdbcExecuteSQLHelper.selectMapPropByResultSetExtractor(sql.toString(),new EmployeeListResultSetExtractor());
    }

    @Override
    public <U> List<Employee> readsAsListFilterBy(U param) {
        return jdbcExecuteSQLHelper.selectAllWhereMapPropByRowMapper(Commands.EMPLOYEE_SELECT_ALL_BY_BIRTHDAY,new EmployeeListResultSetExtractor(),param);
    }

    @Override
    public Iterator<Employee> readsAsIterator() {
        return null;
    }

    @Override
    public <U> Integer deleteAndBackup(U param) {
        StringBuilder sql = new StringBuilder();
        // Note insert select it can insert many rows
        sql.append("INSERT INTO TTKNP_SHOP.EMPLOYEES_BAK ( FIRSTNAME, LASTNAME, BIRTHDAY, ADDRESS) ");
        sql.append("SELECT FIRSTNAME, LASTNAME, BIRTHDAY, ADDRESS ");
        sql.append("FROM TTKNP_SHOP.EMPLOYEES ");
        sql.append("WHERE LASTNAME = '"+param+"'; ");
        sql.append("DELETE FROM TTKNP_SHOP.EMPLOYEES WHERE LASTNAME = '"+param+"'; ");
        return jdbcExecuteSQLHelper.multipleQueries(sql.toString());
    }

    private static class EmployeeListResultSetExtractor implements ResultSetExtractor<List<Employee>> {
        @Override
        public List<Employee> extractData(ResultSet rs) throws SQLException {
            List<Employee> employees = new ArrayList<>();
            while (rs.next()) {
                String uuid = rs.getString("UUID");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String address = rs.getString("address");
                Employee employee = new Employee(firstname,lastname,null,address);
                employee.setUuid(UUID.fromString(uuid));
                employees.add(employee);
            }
            return employees;
        }
    }
}
