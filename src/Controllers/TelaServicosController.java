package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class TelaServicosController implements Initializable {

    @FXML
    private Button BtnAbrir;

    @FXML
    private Button BtnCriar;

    @FXML
    private Button BtnEditar;

    @FXML
    private Button BtnFiltroPesquisa;

    @FXML
    private Circle CREmServico;

    @FXML
    private Circle CRNaoPago;

    @FXML
    private Circle CROrcando;

    @FXML
    private Circle CRPago;

    @FXML
    private HBox CabecalhoTela1;

    @FXML
    private VBox CampEmServico;

    @FXML
    private VBox CampNaoPago;

    @FXML
    private VBox CampOrçando;

    @FXML
    private VBox CampPago;

    @FXML
    private Label ClienteCNPJ;

    @FXML
    private Label ClienteEMAIL;

    @FXML
    private Label ClienteNome;

    @FXML
    private Label ClienteOrcamentoData;

    @FXML
    private Label ClienteOrcamentoNumero;

    @FXML
    private Label ClienteOrcamentoTotal;

    @FXML
    private Label ClienteTelefone1;

    @FXML
    private Label ClienteTelefone2;

    @FXML
    private TextField FiltroPesquisa;

    @FXML
    private HBox HBoxOracamentosMolde1;

    @FXML
    private HBox RodaPe;

    @FXML
    private AnchorPane TelaAncoraPrincipal1;

    @FXML
    private AnchorPane TelaAncoraRolagem1;

    @FXML
    private VBox TelaPrincipal1;

    @FXML
    private ScrollPane TelaRolagem1;

    @FXML
    private Label TxtEmServico;

    @FXML
    private Label TxtNaoPago;

    @FXML
    private Label TxtOrcando;

    @FXML
    private Label TxtPago;

    @FXML
    private VBox VBoxPrincipalRolagem1;

    // Método para abrir uma nova tela
    private void abrirNovaTela(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AbrirTela(ActionEvent event) {
        abrirNovaTela("/projetoretifica/FXML/TelaOrcamento2.fxml"); // Use o caminho completo do FXML se estiver em outro pacote
    }

    @FXML
    void CriarTela(ActionEvent event) {
        abrirNovaTela("/projetoretifica/FXML/TelaOrcamento2.fxml"); // Use o caminho completo do FXML se estiver em outro pacote
    }

    @FXML
    void EditarTela(ActionEvent event) {
        abrirNovaTela("/projetoretifica/FXML/TelaOrcamento2.fxml"); // Use o caminho completo do FXML se estiver em outro pacote
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}