package com.example.financescalculationsoftware.controller;

import java.sql.SQLException;
import java.util.List;

/*
 *@author:<Brenda>
 *@ra:<1110482313042>
 */

public interface IRegistroTransacao<T> {

    void inserir(T t) throws SQLException;
    void atualizar(T t) throws SQLException;
    void deletar(T t) throws SQLException;
    T procurarUm(int id) throws SQLException;
    List<T> procurarTodos() throws SQLException;
}

