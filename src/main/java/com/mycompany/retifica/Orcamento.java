package com.mycompany.retifica;

import java.math.BigDecimal;
import java.util.List;

public class Orcamento {
    private int idOrcamento;
    private String cpf;
    private List<Servico> servicos;
    private List<Peca> pecas;
    private BigDecimal total;
    private boolean statusPagamento;
    private boolean statusPreparo;

    // Construtor
    public Orcamento(int idOrcamento, String cpf, List<Servico> servicos, List<Peca> pecas, boolean statusPagamento, boolean statusPreparo) {
        this.idOrcamento = idOrcamento;
        this.cpf = cpf;
        this.servicos = servicos;
        this.pecas = pecas;
        this.total = calcularTotal();
        this.statusPagamento = statusPagamento;
        this.statusPreparo = statusPreparo;
    }

    // Método para calcular o total
    private BigDecimal calcularTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Servico servico : servicos) {
            total = total.add(servico.getPreco());
        }
        for (Peca peca : pecas) {
            total = total.add(peca.getPreco());
        }
        return total;
    }

    // Getters e Setters
    public int getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public List<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(List<Peca> pecas) {
        this.pecas = pecas;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean isStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(boolean statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public boolean isStatusPreparo() {
        return statusPreparo;
    }

    public void setStatusPreparo(boolean statusPreparo) {
        this.statusPreparo = statusPreparo;
    }
}
