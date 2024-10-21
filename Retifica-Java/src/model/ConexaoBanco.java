package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static final String URL = "jdbc:postgresql://localhost:5432/bd-retifica";

    // Método para obter a conexão com o banco de dados
    public static Connection getConnection(String usuario, String senha) throws SQLException {
        return DriverManager.getConnection(URL, usuario, senha);
    }
}