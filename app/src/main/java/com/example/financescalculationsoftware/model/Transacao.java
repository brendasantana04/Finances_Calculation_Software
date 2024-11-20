package com.example.financescalculationsoftware.model;

/*
 *@author:<Brenda>
 *@ra:<1110482313042>
 */

public abstract class Transacao {
    private int id;
    private String descricao;
    private double valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Transacao{id=" + id + ", descricao='" + descricao + "', valor=" + valor + "}";
    }
}
