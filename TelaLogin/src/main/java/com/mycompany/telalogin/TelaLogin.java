package com.mycompany.telalogin;

import javax.swing.*;
import br.com.login.view.LoginView; // Certifique-se de que este é o caminho correto da classe

public class TelaLogin {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Tela de Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Adiciona o painel LoginView ao JFrame
        LoginView loginView = new LoginView();
        frame.add(loginView);

        frame.setVisible(true);
    }
}
