package com.mycompany.retifica;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControladorLogin {

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Button botaoEntrar;

    @FXML
    private void handleBotaoEntrar() {
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText();

        // Lógica de autenticação aqui
        System.out.println("Tentando logar com usuário: " + usuario + " e senha: " + senha);
    }
}
