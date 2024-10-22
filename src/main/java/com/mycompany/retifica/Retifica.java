package com.mycompany.retifica;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class Retifica {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        // Testar conexão com usuário
        if (ConexaoBanco.verificarUsuario("julio", "1235")) {
            System.out.println("Usuário verificado com sucesso!");
        } else {
            System.out.println("Falha na verificação do usuário.");
        }

        // Testar inserção e verificação de peça
        System.out.print("Digite o tipo de peça: ");
        String tipoPeca = scanner.nextLine();

        System.out.print("Digite o nome da peça: ");
        String nomePeca = scanner.nextLine();

        System.out.print("Digite o preço da peça: ");
        String precoInput = scanner.next();  // Ler como String primeiro
        BigDecimal preco = null;
        try {
            preco = new BigDecimal(precoInput.replace(",", "."));  // Substitui vírgula por ponto
        } catch (NumberFormatException e) {
            System.out.println("Entrada de preço inválida.");
            System.exit(1);
        }

        Peca peca = new Peca(0, tipoPeca, nomePeca, preco);
        ConexaoBanco.inserirPeca(peca);
        if (ConexaoBanco.verificarPeca(nomePeca)) {
            System.out.println("Peça inserida e verificada com sucesso!");
        } else {
            System.out.println("Falha na verificação da peça.");
        }

        scanner.close();
    }
}
