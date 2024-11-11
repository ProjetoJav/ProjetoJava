package Class;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class CriarBancoDeDados {
    public static void main(String[] args) {
        // URL de conexão ao banco de dados SQLite
        String url = "jdbc:sqlite:bd_retifica.db";

        // Carregar explicitamente o driver JDBC do SQLite
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver carregado com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC do SQLite não encontrado!");
            e.printStackTrace();
            return;
        }

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Verificar se a tabela "clientes" já existe
            ResultSet rs = conn.getMetaData().getTables(null, null, "clientes", null);
            if (!rs.next()) {
                // Código SQL para criar as tabelas
                String sql = "CREATE TABLE clientes("
                        + "cpf VARCHAR(11) NOT NULL PRIMARY KEY, "
                        + "idendereco INTEGER NOT NULL, "
                        + "nome VARCHAR(30) NOT NULL, "
                        + "email VARCHAR(50) NOT NULL, "
                        + "telefone_celular VARCHAR(14) NOT NULL, "
                        + "telefone_fixo VARCHAR(8), "
                        + "FOREIGN KEY (idendereco) REFERENCES endereco (idendereco) ON DELETE CASCADE ON UPDATE CASCADE);"

                        + "CREATE TABLE usuarios ("
                        + "idusuario INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "nome VARCHAR(50) UNIQUE NOT NULL, "
                        + "senha VARCHAR(10) NOT NULL);"

                        + "CREATE TABLE endereco ("
                        + "idendereco INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "rua VARCHAR(30) NOT NULL, "
                        + "bairro VARCHAR(20) NOT NULL, "
                        + "cep VARCHAR(8) NOT NULL, "
                        + "complemento VARCHAR(20));"

                        + "CREATE TABLE servico ("
                        + "idservico INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "nomeservico VARCHAR(40) NOT NULL UNIQUE, "
                        + "preco NUMERIC(10,2) CHECK(preco > 0) NOT NULL);"

                        + "CREATE TABLE peca ("
                        + "idpeca INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "tipopeca VARCHAR(40) NOT NULL, "
                        + "nomepeca VARCHAR(40) NOT NULL UNIQUE, "
                        + "preco NUMERIC(10,2) CHECK(preco > 0) NOT NULL);"

                        + "CREATE TABLE orcamento ("
                        + "idorcamento INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "cpf VARCHAR(11) NOT NULL, "
                        + "idservico INTEGER NOT NULL, "
                        + "idpeca INTEGER NOT NULL, "
                        + "idusuario INTEGER NOT NULL, "
                        + "total NUMERIC(10,2) CHECK(total > 0) NOT NULL, "
                        + "status_pagamento BOOLEAN DEFAULT FALSE, "
                        + "status_preparo BOOLEAN DEFAULT FALSE, "
                        + "FOREIGN KEY (cpf) REFERENCES clientes (cpf) ON DELETE CASCADE ON UPDATE CASCADE, "
                        + "FOREIGN KEY (idservico) REFERENCES servico (idservico) ON DELETE CASCADE ON UPDATE CASCADE, "
                        + "FOREIGN KEY (idpeca) REFERENCES peca (idpeca) ON DELETE CASCADE ON UPDATE CASCADE, "
                        + "FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario) ON DELETE CASCADE ON UPDATE CASCADE);"

                        + "CREATE TABLE pecaorcamento ("
                        + "idpeca INTEGER NOT NULL, "
                        + "idorcamento INTEGER NOT NULL, "
                        + "PRIMARY KEY (idpeca, idorcamento), "
                        + "FOREIGN KEY (idpeca) REFERENCES peca (idpeca) ON DELETE CASCADE ON UPDATE CASCADE, "
                        + "FOREIGN KEY (idorcamento) REFERENCES orcamento (idorcamento) ON DELETE CASCADE ON UPDATE CASCADE);"

                        + "CREATE TABLE servicoorcamento ("
                        + "idservico INTEGER NOT NULL, "
                        + "idorcamento INTEGER NOT NULL, "
                        + "PRIMARY KEY (idservico, idorcamento), "
                        + "FOREIGN KEY (idservico) REFERENCES servico (idservico) ON DELETE CASCADE ON UPDATE CASCADE, "
                        + "FOREIGN KEY (idorcamento) REFERENCES orcamento (idorcamento) ON DELETE CASCADE ON UPDATE CASCADE);"

                        + "ALTER TABLE clientes ALTER COLUMN cpf TYPE VARCHAR(14);";
                
                // Executar o código SQL para criar as tabelas
                stmt.executeUpdate(sql);
                System.out.println("Banco de dados e tabelas criados com sucesso!");
            } else {
                System.out.println("Tabelas já existem. Nenhuma ação necessária.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
