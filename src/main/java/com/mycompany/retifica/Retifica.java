package com.mycompany.retifica;

import java.sql.SQLException;

public class Retifica {
    public static void main(String[] args) throws SQLException {
        // Cria um objeto Usuario com os dados do usuário
        Usuario usuario = new Usuario("julio", "1235");

        // Chama o método conectarAoBanco() para conectar ao banco de dados
        usuario.conectarAoBanco();
    }
}