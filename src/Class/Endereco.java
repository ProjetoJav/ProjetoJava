package Class;

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

    public String getRua() { // Método getter para o atributo rua
        return rua; // Retorna o valor do atributo rua
    }

    public void setRua(String rua) { // Método setter para o atributo rua
        this.rua = rua; // Atribui o valor recebido como parâmetro ao atributo rua
    }

    public String getBairro() { // Método getter para o atributo bairro
        return bairro; // Retorna o valor do atributo bairro
    }

    public void setBairro(String bairro) { // Método setter para o atributo bairro
        this.bairro = bairro; // Atribui o valor recebido como parâmetro ao atributo bairro
    }

    public String getCep() { // Método getter para o atributo cep
        return cep; // Retorna o valor do atributo cep
    }

    public void setCep(String cep) { // Método setter para o atributo cep
        this.cep = cep; // Atribui o valor recebido como parâmetro ao atributo cep
    }

    public String getComplemento() { // Método getter para o atributo complemento
        return complemento; // Retorna o valor do atributo complemento
    }

    public void setComplemento(String complemento) { // Método setter para o atributo complemento
        this.complemento = complemento; // Atribui o valor recebido como parâmetro ao atributo complemento
    }
}
