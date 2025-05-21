package com.ttknp.understandbeanpropertyrowmapperjdbc.services.shop.common;


import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public interface ServiceCommon <T> {
    Integer save(T object);
    Integer countRows();
    List<T> readsAsList();
    List<T> readsAsListSpecifyColumn();
    <U> List<T> readsAsListFilterBy(U param);
    Iterator<T> readsAsIterator();
    <U >Integer deleteAndBackup(U param);
}
