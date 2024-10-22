package com.mycompany.retifica;

public class Peca {
    private int idPeca; // Atributo para armazenar o ID da peça (int)
    private String nome; // Atributo para armazenar o nome da peça (String)
    private String tipo; // Atributo para armazenar o tipo da peça (String)
    private double preco; // Atributo para armazenar o preço da peça (double)
    
    // Construtor
    public Peca(int idPeca, String nome, String tipo, double preco) {
        this.idPeca = idPeca; // Atribui o valor do ID da peça recebido como parâmetro ao atributo idPeca
        this.nome = nome; // Atribui o valor do nome recebido como parâmetro ao atributo nome
        this.tipo = tipo; // Atribui o valor do tipo recebido como parâmetro ao atributo tipo
        this.preco = preco; // Atribui o valor do preço recebido como parâmetro ao atributo preco
    }

    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
