package com.example.financescalculationsoftware.persistence;

import java.sql.SQLException;
import java.util.List;

/*
 *@author:<Brenda>
 *@ra:<1110482313042>
 */
public interface ICRUDDao<T> {
    void insert(T t) throws SQLException;
    int update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    T findOne(int id) throws SQLException;
    List<T> findAll() throws SQLException;
}

