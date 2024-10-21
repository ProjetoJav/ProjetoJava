package model;

public class Servico {
    
    private int idServico; // Atributo para armazenar o ID do serviço (int)
    private String nome; // Atributo para armazenar o nome do serviço (String)
    private double preco; // Atributo para armazenar o preço do serviço (double)
    // Construtor
    public Servico(int idServico, String nome, double preco) {
        this.idServico = idServico; // Atribui o valor do ID do serviço recebido como parâmetro ao atributo idServico
        this.nome = nome; // Atribui o valor do nome recebido como parâmetro ao atributo nome
        this.preco = preco; // Atribui o valor do preço recebido como parâmetro ao atributo preco
    }

    // Getters e Setters
    public int getIdServico() { // Método getter para o atributo idServico
        return idServico; // Retorna o valor do atributo idServico
    }

    public void setIdServico(int idServico) { // Método setter para o atributo idServico
        this.idServico = idServico; // Atribui o valor recebido como parâmetro ao atributo idServico
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
