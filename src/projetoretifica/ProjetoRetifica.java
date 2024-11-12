package projetoretifica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProjetoRetifica extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/TelaLogin.fxml"));

        Scene scene = new Scene(root);

        String cssFile = getClass().getResource("/CSS/telaservicos.css").toExternalForm();
        scene.getStylesheets().add(cssFile);

        stage.setScene(scene);
        stage.setTitle("Projeto Retífica");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
