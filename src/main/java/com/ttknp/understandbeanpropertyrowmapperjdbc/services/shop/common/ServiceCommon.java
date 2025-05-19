package com.ttknp.understandbeanpropertyrowmapperjdbc.services.shop.common;

import java.util.Iterator;
import java.util.List;

public interface ServiceCommon <T> {
    Integer save(T object);
    Integer countRows();
    List<T> readsAsList();
    Iterator<T> readsAsIterator();
}
