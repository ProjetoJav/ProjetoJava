package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

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
    void CriarOrcamento(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/TelaOrcamento2 (1).fxml"));
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
        // Lógica para editar orçamento
    }

    @FXML
    void ExcluirOrcamento(ActionEvent event) {
        // Lógica para excluir orçamento
    }
}
