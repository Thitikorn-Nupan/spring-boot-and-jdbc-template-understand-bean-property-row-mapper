package com.ttknp.understandbeanpropertyrowmapperjdbc.services.shop;

import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.school.Student;
import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.shop.Customer;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.jdbc.JdbcExecuteSQLHelper;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.jdbc.NamedParamJdbcExecuteSQLHelper;
import com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.sql_commands.Commands;
import com.ttknp.understandbeanpropertyrowmapperjdbc.services.shop.common.ServiceCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class CustomerService implements ServiceCommon<Customer> {

    private final JdbcExecuteSQLHelper jdbcExecuteSQLHelper;
    private final NamedParamJdbcExecuteSQLHelper namedParamJdbcExecuteSQLHelper;

    @Autowired
    public CustomerService(JdbcExecuteSQLHelper jdbcExecuteSQLHelper, NamedParamJdbcExecuteSQLHelper namedParamJdbcExecuteSQLHelper) {
        this.jdbcExecuteSQLHelper = jdbcExecuteSQLHelper;
        this.namedParamJdbcExecuteSQLHelper = namedParamJdbcExecuteSQLHelper;
    }

    @Override
    public Integer save(Customer object) {
        return namedParamJdbcExecuteSQLHelper.save(Commands.CUSTOMER_INSERT, object);
    }

    @Override
    public Integer countRows() {
        return jdbcExecuteSQLHelper.countRows(Commands.CUSTOMER_SELECT_COUNT);
    }

    @Override
    public List<Customer> readsAsList() {
        return jdbcExecuteSQLHelper.selectAllMapPropByBeanPropertyRowMapper(Commands.CUSTOMER_SELECT_ALL, Customer.class);
    }

    @Override
    public Iterator<Customer> readsAsIterator() {
        return null;
    }
}
