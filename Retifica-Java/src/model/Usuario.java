package model;

import java.sql.SQLException;
import java.sql.Connection;

public class Usuario {

    private String nome;
    private String email;
    private String senha;

    // Construtor da classe Usuario
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getters e setters para os atributos
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Método para realizar a conexão ao banco de dados
    public void conectarAoBanco() throws SQLException {
        // Obtem os dados do usuario
        String usuario = this.getEmail(); // Utiliza o email como nome de usuário
        String senha = this.getSenha();

        // Cria uma conexão usando a classe ConexaoBanco com os dados do usuário
        Connection conexao = ConexaoBanco.getConnection(usuario, senha);

        // Utiliza a conexão para realizar operações no banco de dados
        // ... (Exemplo: inserir dados na tabela "usuarios")
        // PreparedStatement stmt = conexao.prepareStatement("INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)");
        // stmt.setString(1, this.getNome());
        // stmt.setString(2, this.getEmail());
        // stmt.setString(3, this.getSenha());
        // stmt.executeUpdate(); 

        // Fecha a conexão
        conexao.close();
    }
}