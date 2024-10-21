package model;

public class Cliente {
    private String cpf;
    private String nome;
    private String email;
    private String telefoneCelular;
    private String telefoneFixo;
    private Endereco endereco;

    public Cliente(String cpf, String nome, String email, String telefoneCelular, String telefoneFixo, Endereco endereco) {
        this.cpf = cpf; // Atribui o valor do CPF recebido como parâmetro ao atributo cpf
        this.nome = nome; // Atribui o valor do nome recebido como parâmetro ao atributo nome
        this.email = email; // Atribui o valor do email recebido como parâmetro ao atributo email
        this.telefoneCelular = telefoneCelular; // Atribui o valor do telefone celular recebido como parâmetro ao atributo telefoneCelular
        this.telefoneFixo = telefoneFixo; // Atribui o valor do telefone fixo recebido como parâmetro ao atributo telefoneFixo
        this.endereco = endereco; // Atribui o valor do endereço recebido como parâmetro ao atributo endereco
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


}
