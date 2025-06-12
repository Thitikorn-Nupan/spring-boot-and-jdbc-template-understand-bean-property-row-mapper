package com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.jdbc.service;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;

@Component
public class UsefulJdbcService {

    public String getSchemaAndTableNameOnTableAnnotation(Class<?> entityClass) {
        Table tableAnnotation = entityClass.getAnnotation(Table.class);
        if (tableAnnotation != null) {
            return tableAnnotation.schema()+"."+tableAnnotation.name();
        }
        throw new RuntimeException("Schema & Table " + entityClass.getSimpleName() + " has no @Table annotation");
    }

}