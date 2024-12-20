package com.example.financescalculationsoftware.model;

/*
 *@author:<Brenda>
 *@ra:<1110482313042>
 */

public class Despesa extends Transacao {
    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format(
                "Despesa #%d\nDescrição: %s\nValor: R$ %.2f\nCategoria: %s\n",
                getId(),
                getDescricao(),
                getValor(),
                categoria
        );
    }

}

