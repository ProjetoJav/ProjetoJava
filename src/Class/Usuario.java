package Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {
    private String nome;
    private String senha;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public void conectarAoBanco() throws SQLException {
        Connection conn = null;
        try {
            // Registra o driver do PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Conecta ao banco de dados usando o superusuário "postgres"
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bd-retifica", "postgres", "310705");

            // Verifica as credenciais na tabela usuarios
            String sql = "SELECT * FROM usuarios WHERE nome = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.nome);
            stmt.setString(2, this.senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Conexão estabelecida com sucesso!");
            } else {
                System.out.println("Falha na autenticação: usuário ou senha incorretos.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Falha na conexão: " + e.getMessage());
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
