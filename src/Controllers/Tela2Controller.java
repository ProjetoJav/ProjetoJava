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

public class Tela2Controller {

    @FXML
    private Button criar;

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

    private static final String URL = "jdbc:sqlite:C:/Users/julio/Documents/NetBeansProjects/ProjetoJava-main (1)/ProjetoJava-main/db_retifica.db";

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("idOrcamento"));
        clienteCol.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("emailCliente"));
        telefoneCol.setCellValueFactory(new PropertyValueFactory<>("telefoneWhats"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        tableView.setItems(getOrcamentos());
    }

    private ObservableList<Orcamento> getOrcamentos() {
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

                Orcamento orcamento = new Orcamento(idOrcamento, nomeCliente, emailCliente, telefoneWhats, total);
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
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/TelaOrcamento2.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
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

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
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
}
