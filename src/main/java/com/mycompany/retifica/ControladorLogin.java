package com.mycompany.retifica;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        String nome = campoUsuario.getText();
        String senha = campoSenha.getText();

        System.out.println("Tentando logar com usuário: " + nome + " e senha: " + senha);

        ConexaoBanco conexaoBanco = new ConexaoBanco();
        boolean usuarioExiste = conexaoBanco.verificarUsuario(nome, senha);

        if (usuarioExiste) {
            System.out.println("Acesso liberado.");
        } else {
            System.err.println("Usuário ou senha não encontrados.");
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Erro de Login");
            alerta.setHeaderText("Usuário ou senha não encontrados");
            alerta.setContentText("Por favor, verifique seu usuário e senha e tente novamente.");
            alerta.showAndWait();
        }
    }
}
