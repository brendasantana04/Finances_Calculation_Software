package com.example.financescalculationsoftware.controller;

import com.example.financescalculationsoftware.model.Despesa;
import com.example.financescalculationsoftware.persistence.DespesaDao;

import java.sql.SQLException;
import java.util.List;

/*
 *@author:<Brenda>
 *@ra:<1110482313042>
 */

public class DespesaController implements IRegistroTransacao<Despesa> {

    private final DespesaDao despesaDao;

    public DespesaController(DespesaDao despesaDao) {
        this.despesaDao = despesaDao;
    }

    @Override
    public void inserir(Despesa despesa) throws SQLException {
        despesaDao.insert(despesa);
        System.out.println("Despesa inserida: " + despesa);
    }

    @Override
    public void atualizar(Despesa despesa) throws SQLException {
        despesaDao.update(despesa);
        System.out.println("Despesa atualizada: " + despesa);
    }

    @Override
    public void deletar(Despesa despesa) throws SQLException {
        despesaDao.delete(despesa);
        System.out.println("Despesa deletada: " + despesa);
    }

    @Override
    public Despesa procurarUm(int id) throws SQLException {
        return despesaDao.findOne(id);
    }

    @Override
    public List<Despesa> procurarTodos() throws SQLException {
        return despesaDao.findAll();
    }
}

