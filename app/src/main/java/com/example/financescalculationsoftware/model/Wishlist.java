package com.example.financescalculationsoftware.model;

/*
 *@author:<Brenda>
 *@ra:<1110482313042>
 */

public class Wishlist extends Transacao {
    private String prioridade;

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return String.format(
                "Wishlist #%d\nDescrição: %s\nValor: R$ %.2f\nPrioridade: %s\n",
                getId(),
                getDescricao(),
                getValor(),
                prioridade
        );
    }
}

