package com.ttknp.understandbeanpropertyrowmapperjdbc.services.shop;

import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.shop.Customer;
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
    public List<Customer> readsAsListSpecifyColumn() {
        StringBuilder sql = new StringBuilder(Commands.CUSTOMER_SELECT_ALL_FIRSTNAME_START_WITH);
        sql.append(" 'J%'"); // append as concast string like +
        return jdbcExecuteSQLHelper.selectMapPropByResultSetExtractor(sql.toString(),new CustomerListResultSetExtractor());
    }

    @Override
    public <U> List<Customer> readsAsListFilterBy(U param) {
        return jdbcExecuteSQLHelper.selectAllWhereMapPropByRowMapper(Commands.CUSTOMER_SELECT_ALL_BY_LASTNAME,new CustomerListResultSetExtractor(),param);
    }

    @Override
    public Iterator<Customer> readsAsIterator() {
        return null;
    }

    @Override
    public <U> Integer deleteAndBackup(U param) {
        StringBuilder sql = new StringBuilder();
        // Note insert select it can insert many rows
        sql.append("INSERT INTO TTKNP_SHOP.CUSTOMERS_BAK ( FIRSTNAME, LASTNAME, BIRTHDAY, ADDRESS) ");
        sql.append("SELECT FIRSTNAME, LASTNAME, BIRTHDAY, ADDRESS ");
        sql.append("FROM TTKNP_SHOP.CUSTOMERS ");
        sql.append("WHERE UUID = '"+param+"'; ");
        sql.append("DELETE FROM TTKNP_SHOP.CUSTOMERS WHERE UUID = '"+param+"'; ");
        return jdbcExecuteSQLHelper.multipleQueries(sql.toString());
    }

    private static class CustomerListResultSetExtractor implements ResultSetExtractor<List<Customer>> {
        @Override
        public List<Customer> extractData(ResultSet rs) throws SQLException {
            List<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                String uuid = rs.getString("UUID");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String address = rs.getString("address");
                Customer customer = new Customer(firstname,lastname,null,address);
                customer.setUuid(UUID.fromString(uuid));
                customers.add(customer);
            }
            return customers;
        }
    }


}
