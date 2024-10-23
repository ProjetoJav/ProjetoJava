package com.mycompany.retifica;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Retifica {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Inserir Orçamento");
            System.out.println("2. Atualizar Orçamento");
            System.out.println("3. Deletar Orçamento");
            System.out.println("4. Listar Orçamentos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir newline

            switch (opcao) {
                case 1:
                    inserirOrcamento(scanner);
                    break;
                case 2:
                    atualizarOrcamento(scanner);
                    break;
                case 3:
                    deletarOrcamento(scanner);
                    break;
                case 4:
                    listarOrcamentos();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void inserirOrcamento(Scanner scanner) throws SQLException {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        List<Servico> servicos = new ArrayList<>();
        List<Peca> pecas = new ArrayList<>();

        // Adicionar serviços ao orçamento
        System.out.print("Quantos serviços deseja adicionar? ");
        int numServicos = scanner.nextInt();
        scanner.nextLine();  // Consumir newline
        for (int i = 0; i < numServicos; i++) {
            System.out.print("Digite o ID do serviço: ");
            int idServico = scanner.nextInt();
            scanner.nextLine(); // Consumir newline
            Servico servico = ConexaoBanco.buscarServico(idServico);
            if (servico != null) {
                servicos.add(servico);
            } else {
                System.out.println("Serviço com ID " + idServico + " não encontrado.");
            }
        }

        // Adicionar peças ao orçamento
        System.out.print("Quantas peças deseja adicionar? ");
        int numPecas = scanner.nextInt();
        scanner.nextLine();  // Consumir newline
        for (int i = 0; i < numPecas; i++) {
            System.out.print("Digite o ID da peça: ");
            int idPeca = scanner.nextInt();
            scanner.nextLine(); // Consumir newline
            Peca peca = ConexaoBanco.buscarPeca(idPeca);
            if (peca != null) {
                pecas.add(peca);
            } else {
                System.out.println("Peça com ID " + idPeca + " não encontrada.");
            }
        }

        if (!servicos.isEmpty() && !pecas.isEmpty()) {
            Orcamento orcamento = new Orcamento(0, cpf, servicos, pecas, false, false);
            ConexaoBanco.inserirOrcamento(orcamento);
            System.out.println("Orçamento inserido com sucesso!");
        } else {
            System.out.println("Erro: Não foi possível criar o orçamento. Verifique se os serviços e peças existem.");
        }
    }

    private static void atualizarOrcamento(Scanner scanner) throws SQLException {
        System.out.print("Digite o ID do orçamento a atualizar: ");
        int idOrcamento = scanner.nextInt();
        scanner.nextLine();  // Consumir newline

        // Digite novamente os serviços e peças
        List<Servico> servicos = new ArrayList<>();
        List<Peca> pecas = new ArrayList<>();

        // Adicionar serviços ao orçamento
        System.out.print("Quantos serviços deseja adicionar? ");
        int numServicos = scanner.nextInt();
        scanner.nextLine();  // Consumir newline
        for (int i = 0; i < numServicos; i++) {
            System.out.print("Digite o ID do serviço: ");
            int idServico = scanner.nextInt();
            scanner.nextLine(); // Consumir newline
            Servico servico = ConexaoBanco.buscarServico(idServico);
            if (servico != null) {
                servicos.add(servico);
            } else {
                System.out.println("Serviço com ID " + idServico + " não encontrado.");
            }
        }

        // Adicionar peças ao orçamento
        System.out.print("Quantas peças deseja adicionar? ");
        int numPecas = scanner.nextInt();
        scanner.nextLine();  // Consumir newline
        for (int i = 0; i < numPecas; i++) {
            System.out.print("Digite o ID da peça: ");
            int idPeca = scanner.nextInt();
            scanner.nextLine(); // Consumir newline
            Peca peca = ConexaoBanco.buscarPeca(idPeca);
            if (peca != null) {
                pecas.add(peca);
            } else {
                System.out.println("Peça com ID " + idPeca + " não encontrada.");
            }
        }

        if (!servicos.isEmpty() && !pecas.isEmpty()) {
            Orcamento orcamento = new Orcamento(idOrcamento, "", servicos, pecas, false, false);
            ConexaoBanco.atualizarOrcamento(orcamento);
            System.out.println("Orçamento atualizado com sucesso!");
        } else {
            System.out.println("Erro: Não foi possível atualizar o orçamento. Verifique se os serviços e peças existem.");
        }
    }

    private static void deletarOrcamento(Scanner scanner) throws SQLException {
        System.out.print("Digite o ID do orçamento a deletar: ");
        int idOrcamento = scanner.nextInt();
        scanner.nextLine();  // Consumir newline
        ConexaoBanco.deletarOrcamento(idOrcamento);
        System.out.println("Orçamento deletado com sucesso!");
    }

    private static void listarOrcamentos() throws SQLException {
        List<Orcamento> orcamentos = ConexaoBanco.buscarTodosOrcamentos();
        for (Orcamento orcamento : orcamentos) {
            System.out.println("ID: " + orcamento.getIdOrcamento());
            System.out.println("CPF: " + orcamento.getCpf());
            System.out.println("Total: " + orcamento.getTotal());
            System.out.println("Serviços: ");
            for (Servico servico : orcamento.getServicos()) {
                System.out.println(" - " + servico.getNomeServico() + " (Preço: " + servico.getPreco() + ")");
            }
            System.out.println("Peças: ");
            for (Peca peca : orcamento.getPecas()) {
                System.out.println(" - " + peca.getNomePeca() + " (Preço: " + peca.getPreco() + ")");
            }
            System.out.println("Status Pagamento: " + (orcamento.isStatusPagamento() ? "Pago" : "Não Pago"));
            System.out.println("Status Preparo: " + (orcamento.isStatusPreparo() ? "Concluído" : "Em Preparo"));
            System.out.println();
        }
    }
}
