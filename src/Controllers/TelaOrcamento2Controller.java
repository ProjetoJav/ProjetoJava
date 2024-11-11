package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TelaOrcamento2Controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private HBox InfoRetifica;

    @FXML
    private TextField cliente;

    @FXML
    private TextField cnpj;

    @FXML
    private TextField descontoServicos;

    @FXML
    private TextField endereco;

    @FXML
    private TextField entrada;

    @FXML
    private TextField horas;

    @FXML
    private TextField numeroOrcamento;

    @FXML
    private TextArea observacoes;

    @FXML
    private TextField rg;

    @FXML
    private TableView<?> tableOrcamento;

    @FXML
    private TableView<?> tablePecas;

    @FXML
    private TableView<?> tableServicosSemDesc;

    @FXML
    private TextField telefone;

    @FXML
    private TextField total;

    @FXML
    private TextField totalAPagar;

}
