package Class;

import java.math.BigDecimal;

public class Peca {
    private int idPeca;
    private String tipoPeca;
    private String nomePeca;
    private BigDecimal preco;

    // Construtor
    public Peca(int idPeca, String tipoPeca, String nomePeca, BigDecimal preco) {
        this.idPeca = idPeca;
        this.tipoPeca = tipoPeca;
        this.nomePeca = nomePeca;
        this.preco = preco;
    }

    // Getters e Setters
    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }

    public String getTipoPeca() {
        return tipoPeca;
    }

    public void setTipoPeca(String tipoPeca) {
        this.tipoPeca = tipoPeca;
    }

    public String getNomePeca() {
        return nomePeca;
    }

    public void setNomePeca(String nomePeca) {
        this.nomePeca = nomePeca;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
