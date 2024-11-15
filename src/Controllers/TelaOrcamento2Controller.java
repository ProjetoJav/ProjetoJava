package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Class.Orcamento;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.math.BigDecimal;
import java.sql.ResultSet;

public class TelaOrcamento2Controller {

    @FXML
    private TextField cliente;
    
    @FXML
    private TextField Autoid;

    @FXML
    private Button concluir;

    @FXML
    private TextField email;

    @FXML
    private TextField telefone;

    @FXML
    private TextField total;

    private static final String URL = "jdbc:sqlite:C:/Users/julio/Documents/NetBeansProjects/ProjetoJava-main (1)/ProjetoJava-main/db_retifica.db";
    private Orcamento orcamentoSelecionado;

    @FXML
    public void initialize() {
        criarTabelaOrcamentos();
        if (orcamentoSelecionado == null) {
            Autoid.setText(String.valueOf(getProximoIdOrcamento()));
        }
    }

    @FXML
    void Concluir(ActionEvent event) {
        String cliente = this.cliente.getText();
        String email = this.email.getText();
        String telefone = this.telefone.getText();
        BigDecimal total = new BigDecimal(this.total.getText());

        if (orcamentoSelecionado != null) {
            atualizarOrcamento(cliente, email, telefone, total);
        } else {
            inserirOrcamento(cliente, email, telefone, total);
        }

        voltarParaTela2(event);
    }

    public void carregarOrcamento(Orcamento orcamento) {
        this.orcamentoSelecionado = orcamento;
        this.cliente.setText(orcamento.getNomeCliente());
        this.email.setText(orcamento.getEmailCliente());
        this.telefone.setText(orcamento.getTelefoneWhats());
        this.total.setText(orcamento.getTotal().toString());
        this.Autoid.setText(String.valueOf(orcamento.getIdOrcamento()));
    }

    private void criarTabelaOrcamentos() {
        String sql = "CREATE TABLE IF NOT EXISTS orcamento ("
                   + "idorcamento INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "nomecliente VARCHAR(50) NOT NULL,"
                   + "emailcliente VARCHAR(50) NOT NULL,"
                   + "telefonewhats VARCHAR(14) NOT NULL,"
                   + "total NUMERIC(10,2) CHECK(total > 0) NOT NULL"
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

    private void inserirOrcamento(String cliente, String email, String telefone, BigDecimal total) {
        String sql = "INSERT INTO orcamento (nomecliente, emailcliente, telefonewhats, total) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente);
            pstmt.setString(2, email);
            pstmt.setString(3, telefone);
            pstmt.setBigDecimal(4, total);
            pstmt.executeUpdate();
            System.out.println("Orçamento inserido com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir orçamento.");
        }
    }

    private void atualizarOrcamento(String cliente, String email, String telefone, BigDecimal total) {
        String sql = "UPDATE orcamento SET nomecliente = ?, emailcliente = ?, telefonewhats = ?, total = ? WHERE idorcamento = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente);
            pstmt.setString(2, email);
            pstmt.setString(3, telefone);
            pstmt.setBigDecimal(4, total);
            pstmt.setInt(5, orcamentoSelecionado.getIdOrcamento());
            pstmt.executeUpdate();
            System.out.println("Orçamento atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar orçamento.");
        }
    }

    private void voltarParaTela2(ActionEvent event) {
        try {
            Tela2Controller tela2Controller = Tela2Controller.getInstance();
            tela2Controller.atualizarTabela();
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getProximoIdOrcamento() {
        String sql = "SELECT MAX(idorcamento) + 1 AS proximoId FROM orcamento";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("proximoId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1; // Retorna 1 se não houver registros
    }
}
