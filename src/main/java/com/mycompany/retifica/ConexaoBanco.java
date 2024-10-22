package com.mycompany.retifica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoBanco {
    private static final String URL = "jdbc:postgresql://localhost:5432/bd-retifica";
    private static final String USER = "postgres";
    private static final String PASSWORD = "310705";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean verificarUsuario(String nome, String senha) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM usuarios WHERE nome = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public static void inserirEndereco(Endereco endereco) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO endereco (rua, bairro, cep, complemento) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getBairro());
            stmt.setString(3, endereco.getCep());
            stmt.setString(4, endereco.getComplemento());
            stmt.executeUpdate();
        }
    }

    public static boolean verificarEndereco(String rua) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM endereco WHERE rua = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, rua);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    // Adicionando métodos para a tabela peca
    public static void inserirPeca(Peca peca) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO peca (tipopeca, nomepeca, preco) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, peca.getTipoPeca());
            stmt.setString(2, peca.getNomePeca());
            stmt.setBigDecimal(3, peca.getPreco());
            stmt.executeUpdate();
        }
    }
    
        // Método para buscar endereço por ID
    public static Endereco buscarEndereco(int idEndereco) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM endereco WHERE idendereco = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEndereco);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Endereco(
                    rs.getInt("idendereco"),
                    rs.getString("rua"),
                    rs.getString("bairro"),
                    rs.getString("cep"),
                    rs.getString("complemento")
                );
            }
            return null;
        }
    }

    // Método para atualizar um endereço
    public static void atualizarEndereco(Endereco endereco) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE endereco SET rua = ?, bairro = ?, cep = ?, complemento = ? WHERE idendereco = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getBairro());
            stmt.setString(3, endereco.getCep());
            stmt.setString(4, endereco.getComplemento());
            stmt.setInt(5, endereco.getIdEndereco());
            stmt.executeUpdate();
        }
    }

    // Método para deletar um endereço
    public static void deletarEndereco(int idEndereco) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM endereco WHERE idendereco = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEndereco);
            stmt.executeUpdate();
        }
    }


    public static boolean verificarPeca(String nomePeca) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM peca WHERE nomepeca = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomePeca);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
    
        // Método para buscar peça por ID
    public static Peca buscarPeca(int idPeca) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM peca WHERE idpeca = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPeca);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Peca(
                    rs.getInt("idpeca"),
                    rs.getString("tipopeca"),
                    rs.getString("nomepeca"),
                    rs.getBigDecimal("preco")
                );
            }
            return null;
        }
    }

    // Método para atualizar uma peça
    public static void atualizarPeca(Peca peca) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE peca SET tipopeca = ?, nomepeca = ?, preco = ? WHERE idpeca = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, peca.getTipoPeca());
            stmt.setString(2, peca.getNomePeca());
            stmt.setBigDecimal(3, peca.getPreco());
            stmt.setInt(4, peca.getIdPeca());
            stmt.executeUpdate();
        }
    }

    // Método para deletar uma peça
    public static void deletarPeca(int idPeca) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM peca WHERE idpeca = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPeca);
            stmt.executeUpdate();
        }
    }

}





