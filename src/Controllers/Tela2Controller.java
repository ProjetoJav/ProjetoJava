package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Class.Orcamento;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.math.BigDecimal;
import javafx.scene.control.TextField;

public class Tela2Controller {

    @FXML
    private Button criar;
    
    @FXML
    private TextField pesquisar;

    @FXML
    private Button editar;

    @FXML
    private Button excluir;

    @FXML
    private VBox mainpane;

    @FXML
    private TableView<Orcamento> tableView;

    @FXML
    private TableColumn<Orcamento, Integer> idCol;

    @FXML
    private TableColumn<Orcamento, String> clienteCol;

    @FXML
    private TableColumn<Orcamento, String> emailCol;

    @FXML
    private TableColumn<Orcamento, String> telefoneCol;

    @FXML
    private TableColumn<Orcamento, BigDecimal> totalCol;
    
    @FXML
    private TableColumn<Orcamento, String> dataCol;


    private static final String URL = "jdbc:sqlite:C:/Users/julio/Documents/NetBeansProjects/ProjetoJava-main (1)/ProjetoJava-main/db_retifica.db";

    private static Tela2Controller instance;

    public Tela2Controller() {
        instance = this;
    }

    public static Tela2Controller getInstance() {
        return instance;
    }

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("idOrcamento")); 
        clienteCol.setCellValueFactory(new PropertyValueFactory<>("nomeCliente")); 
        emailCol.setCellValueFactory(new PropertyValueFactory<>("emailCliente")); 
        telefoneCol.setCellValueFactory(new PropertyValueFactory<>("telefoneWhats")); 
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total")); 
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data")); 
        
        atualizarTabela();
    }

    public void atualizarTabela() {
        tableView.setItems(getOrcamentos());
    }

    public static ObservableList<Orcamento> getOrcamentos() {
    ObservableList<Orcamento> orcamentos = FXCollections.observableArrayList();
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


    @FXML
    void CriarOrcamento(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/TelaOrcamento2 (1).fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void EditarOrcamento(ActionEvent event) {
        Orcamento selectedOrcamento = tableView.getSelectionModel().getSelectedItem();
        if (selectedOrcamento != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/TelaOrcamento2 (1).fxml"));
                Parent root = loader.load();

                TelaOrcamento2Controller controller = loader.getController();
                controller.carregarOrcamento(selectedOrcamento);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void ExcluirOrcamento(ActionEvent event) {
        Orcamento selectedOrcamento = tableView.getSelectionModel().getSelectedItem();
        if (selectedOrcamento != null) {
            deletarOrcamento(selectedOrcamento.getIdOrcamento());
            tableView.getItems().remove(selectedOrcamento);
        }
    }

    private void deletarOrcamento(int idOrcamento) {
        String sqlDelete = "DELETE FROM orcamento WHERE idorcamento = ?";
        String sqlResetSeq = "UPDATE sqlite_sequence SET seq = (SELECT MAX(idorcamento) FROM orcamento) WHERE name = 'orcamento'";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmtDelete = conn.prepareStatement(sqlDelete);
             Statement stmtResetSeq = conn.createStatement()) {

            pstmtDelete.setInt(1, idOrcamento);
            pstmtDelete.executeUpdate();

            stmtResetSeq.executeUpdate(sqlResetSeq);

            System.out.println("Orçamento excluído com sucesso e sequência ajustada.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao excluir o orçamento.");
        }
    }

    @FXML
    void Pesquisar(ActionEvent event) {
        String filtro = pesquisar.getText();
        tableView.setItems(getOrcamentos(filtro));
    }


    private ObservableList<Orcamento> getOrcamentos(String filtro) {
    ObservableList<Orcamento> orcamentos = FXCollections.observableArrayList();
    String sql = "SELECT * FROM orcamento";

    if (!filtro.isEmpty()) {
        sql += " WHERE idorcamento LIKE ? OR nomecliente LIKE ? OR emailcliente LIKE ? OR telefonewhats LIKE ? OR total LIKE ? OR data LIKE ?";
    }

    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        if (!filtro.isEmpty()) {
            String queryParam = "%" + filtro + "%";
            pstmt.setString(1, queryParam);
            pstmt.setString(2, queryParam);
            pstmt.setString(3, queryParam);
            pstmt.setString(4, queryParam);
            pstmt.setString(5, queryParam);
            pstmt.setString(6, queryParam);
        }

        ResultSet rs = pstmt.executeQuery();

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


