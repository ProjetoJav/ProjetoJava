package Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaLoginController {

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField senha;

    @FXML
    private Label label;

    @FXML
    private Button login;

    private static final String URL = "jdbc:sqlite:C:/Users/julio/Documents/NetBeansProjects/ProjetoJava-main (1)/ProjetoJava-main/db_retifica.db";

    @FXML
    public void initialize() {
        criarTabelaUsuarios();
    }

    @FXML
    void login(ActionEvent event) {
        String usuario = this.usuario.getText();
        String senha = this.senha.getText();
        
        if (verificarCredenciais(usuario, senha)) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/Tela2.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            label.setText("Usuário e senha incorretos");
        }
    }

    private boolean verificarCredenciais(String usuario, String senha) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE nome = ? AND senha = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void criarTabelaUsuarios() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome TEXT NOT NULL,"
                + "senha TEXT NOT NULL"
                + ");";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
