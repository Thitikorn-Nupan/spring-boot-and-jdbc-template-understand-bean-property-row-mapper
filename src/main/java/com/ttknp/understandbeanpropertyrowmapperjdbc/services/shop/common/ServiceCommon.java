package com.ttknp.understandbeanpropertyrowmapperjdbc.services.shop.common;

import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.Iterator;
import java.util.List;

public interface ServiceCommon <T> {
    Integer save(T object);
    Integer countRows();
    List<T> readsAsList();
    List<T> readsAsListSpecifyColumn();
    Iterator<T> readsAsIterator();
}
