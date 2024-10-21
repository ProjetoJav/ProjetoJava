package model;

public class Endereco {
    private int idEndereco; // Atributo para armazenar o ID do endereço (int)
    private String rua; // Atributo para armazenar a rua do endereço (String)
    private String bairro; // Atributo para armazenar o bairro do endereço (String)
    private String cep; // Atributo para armazenar o CEP do endereço (String)
    private String complemento; // Atributo para armazenar o complemento do endereço (String)

    // Construtor
    public Endereco(int idEndereco, String rua, String bairro, String cep, String complemento) {
        this.idEndereco = idEndereco; // Atribui o valor do ID do endereço recebido como parâmetro ao atributo idEndereco
        this.rua = rua; // Atribui o valor da rua recebido como parâmetro ao atributo rua
        this.bairro = bairro; // Atribui o valor do bairro recebido como parâmetro ao atributo bairro
        this.cep = cep; // Atribui o valor do CEP recebido como parâmetro ao atributo cep
        this.complemento = complemento; // Atribui o valor do complemento recebido como parâmetro ao atributo complemento
    }

    // Getters e Setters
    public int getIdEndereco() { // Método getter para o atributo idEndereco
        return idEndereco; // Retorna o valor do atributo idEndereco
    }

    public void setIdEndereco(int idEndereco) { // Método setter para o atributo idEndereco
        this.idEndereco = idEndereco; // Atribui o valor recebido como parâmetro ao atributo idEndereco
    }

    public String getrua() { // Método getter para o atributo idEndereco
        return rua; // Retorna o valor do atributo idEndereco
    }
    
    public void setrua(String rua) { // Método setter para o atributo idEndereco
        this.rua = rua; // Atribui o valor recebido como parâmetro ao atributo idEndereco
    }

    public String getbairro(){
        return bairro;
    }

    public String setbairro(String bairro){
        return this.bairro = bairro;
    }

    public String getcep(){
        return cep;
    }

    public String setcep(String cep){
        return this.cep = cep;
    }

    public String getcomplento(){
        return complemento;
    }

    public String setcomplemento(String complento){
        return this.complemento = complento;
    }

    

}