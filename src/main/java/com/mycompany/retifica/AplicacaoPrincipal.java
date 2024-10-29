package com.mycompany.retifica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AplicacaoPrincipal extends Application {

    @Override
    public void start(Stage palcoPrincipal) throws Exception {
        Parent raiz = FXMLLoader.load(getClass().getResource("/TelaDeLogin.fxml"));
        Scene cena = new Scene(raiz);
        palcoPrincipal.setScene(cena);
        palcoPrincipal.setTitle("Login");
        palcoPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
