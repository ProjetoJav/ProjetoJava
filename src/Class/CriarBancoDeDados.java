package Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class CriarBancoDeDados {
    public static void main(String[] args) {
        // URL de conexão ao banco de dados SQLite
        String url = "jdbc:sqlite:C:/Users/julio/Documents/NetBeansProjects/ProjetoJava-main (1)/ProjetoJava-main/db_retifica.db";

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

            // Verificar se a tabela "usuarios" já existe
            ResultSet rsUsuarios = conn.getMetaData().getTables(null, null, "usuarios", null);
            if (!rsUsuarios.next()) {
                // Código SQL para criar a tabela "usuarios"
                String sqlUsuarios = "CREATE TABLE usuarios ("
                        + "idusuario INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "nome VARCHAR(50) UNIQUE NOT NULL, "
                        + "senha VARCHAR(10) NOT NULL);";
                stmt.executeUpdate(sqlUsuarios);
                System.out.println("Tabela 'usuarios' criada com sucesso!");
            } else {
                System.out.println("Tabela 'usuarios' já existe.");
            }

            // Verificar se a tabela "orcamento" já existe
            ResultSet rsOrcamento = conn.getMetaData().getTables(null, null, "orcamento", null);
            if (!rsOrcamento.next()) {
                // Código SQL para criar a tabela "orcamento"
                String sqlOrcamento = "CREATE TABLE orcamento ("
                        + "idorcamento INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "nomecliente VARCHAR(50) NOT NULL, "
                        + "emailcliente VARCHAR(50) NOT NULL, "
                        + "telefonewhats VARCHAR(14) NOT NULL, "
                        + "total NUMERIC(10,2) CHECK(total > 0) NOT NULL);";
                stmt.executeUpdate(sqlOrcamento);
                System.out.println("Tabela 'orcamento' criada com sucesso!");
            } else {
                System.out.println("Tabela 'orcamento' já existe.");
            }

            // Remover as tabelas que não serão usadas
            String[] tablesToDrop = {"clientes", "endereco", "servico", "peca", "pecaorcamento", "servicoorcamento"};
            for (String table : tablesToDrop) {
                String sqlDrop = "DROP TABLE IF EXISTS " + table + ";";
                stmt.executeUpdate(sqlDrop);
            }
            System.out.println("Tabelas não utilizadas removidas com sucesso!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
