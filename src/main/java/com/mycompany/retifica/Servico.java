package com.mycompany.retifica;

import java.math.BigDecimal;

public class Servico {
    private int idServico;
    private String nomeServico;
    private BigDecimal preco;

    // Construtor
    public Servico(int idServico, String nomeServico, BigDecimal preco) {
        this.idServico = idServico;
        this.nomeServico = nomeServico;
        this.preco = preco;
    }

    // Getters e Setters
    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}