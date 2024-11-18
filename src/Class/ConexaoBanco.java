package Class;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConexaoBanco {
    
    private static final String URL = "jdbc:sqlite:C:/Users/julio/Documents/NetBeansProjects/ProjetoJava-main (1)/ProjetoJava-main/db_retifica.db";

    public void criarTabelaOrcamento() {
    String sql = "CREATE TABLE IF NOT EXISTS orcamento ("
               + "idorcamento INTEGER PRIMARY KEY AUTOINCREMENT,"
               + "nomecliente VARCHAR(50) NOT NULL,"
               + "emailcliente VARCHAR(50) NOT NULL,"
               + "telefonewhats VARCHAR(14) NOT NULL,"
               + "total NUMERIC(10,2) CHECK(total > 0) NOT NULL,"
               + "data DATETIME NOT NULL"
               + ");";

    try (Connection conn = DriverManager.getConnection(URL);
         Statement stmt = conn.createStatement()) {
        stmt.execute(sql);
        System.out.println("Tabela 'orcamento' verificada/criada com sucesso.");
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Erro ao verificar/criar a tabela 'orcamento'.");
    }
}


    public boolean inserirOrcamento(Orcamento orcamento) {
    String sql = "INSERT INTO orcamento (nomecliente, emailcliente, telefonewhats, total, data) VALUES (?, ?, ?, ?, ?)";
    String dataAtual = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, orcamento.getNomeCliente());
        pstmt.setString(2, orcamento.getEmailCliente());
        pstmt.setString(3, orcamento.getTelefoneWhats());
        pstmt.setBigDecimal(4, orcamento.getTotal());
        pstmt.setString(5, dataAtual);
        pstmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    public List<Orcamento> buscarOrcamentos() {
    List<Orcamento> orcamentos = new ArrayList<>();
    String sql = "SELECT * FROM orcamento";
    
    try (Connection conn = DriverManager.getConnection(URL);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            int idOrcamento = rs.getInt("idorcamento");
            String nomeCliente = rs.getString("nomecliente");
            String emailCliente = rs.getString("emailcliente");
            String telefoneWhats = rs.getString("telefonewhats");
            BigDecimal total = rs.getBigDecimal("total");
            String data = rs.getString("data");

            Orcamento orcamento = new Orcamento(idOrcamento, nomeCliente, emailCliente, telefoneWhats, total, data);
            orcamentos.add(orcamento);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return orcamentos;
}

}
