package Class;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConexaoBanco {
    private static final String URL = "jdbc:postgresql://localhost:5432/bd-retifica";
    private static final String USER = "postgres";
    private static final String PASSWORD = "310705";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public boolean verificarUsuario(String nome, String senha) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE nome = ? AND senha = ?";
        
        try (Connection conn = getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nome);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Usuário encontrado
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Usuário não encontrado
    }

    public static void inserirEndereco(Endereco endereco) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO endereco (rua, bairro, cep, complemento) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getBairro());
            stmt.setString(3, endereco.getCep());
            stmt.setString(4, endereco.getComplemento());
            stmt.executeUpdate();
        }
    }

    public static boolean verificarEndereco(String rua) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM endereco WHERE rua = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, rua);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
    
        // Método para buscar endereço por ID
    public static Endereco buscarEndereco(int idEndereco) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM endereco WHERE idendereco = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEndereco);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Endereco(
                    rs.getInt("idendereco"),
                    rs.getString("rua"),
                    rs.getString("bairro"),
                    rs.getString("cep"),
                    rs.getString("complemento")
                );
            }
            return null;
        }
    }

    // Método para atualizar um endereço
    public static void atualizarEndereco(Endereco endereco) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE endereco SET rua = ?, bairro = ?, cep = ?, complemento = ? WHERE idendereco = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getBairro());
            stmt.setString(3, endereco.getCep());
            stmt.setString(4, endereco.getComplemento());
            stmt.setInt(5, endereco.getIdEndereco());
            stmt.executeUpdate();
        }
    }

    // Método para deletar um endereço
    public static void deletarEndereco(int idEndereco) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM endereco WHERE idendereco = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEndereco);
            stmt.executeUpdate();
        }
    }

    // Adicionando métodos para a tabela peca
    public static void inserirPeca(Peca peca) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO peca (tipopeca, nomepeca, preco) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, peca.getTipoPeca());
            stmt.setString(2, peca.getNomePeca());
            stmt.setBigDecimal(3, peca.getPreco());
            stmt.executeUpdate();
        }
    }

    public static boolean verificarPeca(String nomePeca) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM peca WHERE nomepeca = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomePeca);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
    
        // Método para buscar peça por ID
    public static Peca buscarPeca(int idPeca) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM peca WHERE idpeca = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPeca);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Peca(
                    rs.getInt("idpeca"),
                    rs.getString("tipopeca"),
                    rs.getString("nomepeca"),
                    rs.getBigDecimal("preco")
                );
            }
            return null;
        }
    }

    // Método para atualizar uma peça
    public static void atualizarPeca(Peca peca) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE peca SET tipopeca = ?, nomepeca = ?, preco = ? WHERE idpeca = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, peca.getTipoPeca());
            stmt.setString(2, peca.getNomePeca());
            stmt.setBigDecimal(3, peca.getPreco());
            stmt.setInt(4, peca.getIdPeca());
            stmt.executeUpdate();
        }
    }

    // Método para deletar uma peça
    public static void deletarPeca(int idPeca) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM peca WHERE idpeca = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPeca);
            stmt.executeUpdate();
        }
    }
    
    // Método para inserir serviço
    public static void inserirServico(Servico servico) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO servico (nomeservico, preco) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, servico.getNomeServico());
            stmt.setBigDecimal(2, servico.getPreco());
            stmt.executeUpdate();
        }
    }

    // Método para atualizar serviço
    public static void atualizarServico(Servico servico) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE servico SET nomeservico = ?, preco = ? WHERE idservico = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, servico.getNomeServico());
            stmt.setBigDecimal(2, servico.getPreco());
            stmt.setInt(3, servico.getIdServico());
            stmt.executeUpdate();
        }
    }

    // Método para deletar serviço
    public static void deletarServico(int idServico) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM servico WHERE idservico = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idServico);
            stmt.executeUpdate();
        }
    }

    // Método para buscar serviço por ID
    public static Servico buscarServico(int idServico) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM servico WHERE idservico = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idServico);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Servico(
                    rs.getInt("idservico"),
                    rs.getString("nomeservico"),
                    rs.getBigDecimal("preco")
                );
            }
            return null;
        }
    }
    
    // Método para inserir cliente
    public static void inserirCliente(Cliente cliente) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO clientes (cpf, idendereco, nome, email, telefone_celular, telefone_fixo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getCpf());
            stmt.setInt(2, cliente.getIdEndereco());
            stmt.setString(3, cliente.getNome());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getTelefoneCelular());
            stmt.setString(6, cliente.getTelefoneFixo());
            stmt.executeUpdate();
        }
    }

    // Método para atualizar cliente
    public static void atualizarCliente(Cliente cliente) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE clientes SET idendereco = ?, nome = ?, email = ?, telefone_celular = ?, telefone_fixo = ? WHERE cpf = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cliente.getIdEndereco());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefoneCelular());
            stmt.setString(5, cliente.getTelefoneFixo());
            stmt.setString(6, cliente.getCpf());
            stmt.executeUpdate();
        }
    }

    // Método para deletar cliente
    public static void deletarCliente(String cpf) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM clientes WHERE cpf = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
    }

    // Método para buscar cliente por CPF
    public static Cliente buscarCliente(String cpf) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM clientes WHERE cpf = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getString("cpf"),
                    rs.getInt("idendereco"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone_celular"),
                    rs.getString("telefone_fixo")
                );
            }
            return null;

   
        }
    }

    // Método para inserir orçamento
    public static void inserirOrcamento(Orcamento orcamento) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO orcamento (cpf, total, status_pagamento, status_preparo) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, orcamento.getCpf());
            stmt.setBigDecimal(2, orcamento.getTotal());
            stmt.setBoolean(3, orcamento.isStatusPagamento());
            stmt.setBoolean(4, orcamento.isStatusPreparo());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idOrcamento = generatedKeys.getInt(1);

                for (Servico servico : orcamento.getServicos()) {
                    String sqlServico = "INSERT INTO servicoorcamento (idservico, idorcamento) VALUES (?, ?)";
                    try (PreparedStatement stmtServico = conn.prepareStatement(sqlServico)) {
                        stmtServico.setInt(1, servico.getIdServico());
                        stmtServico.setInt(2, idOrcamento);
                        stmtServico.executeUpdate();
                    }
                }

                for (Peca peca : orcamento.getPecas()) {
                    String sqlPeca = "INSERT INTO pecaorcamento (idpeca, idorcamento) VALUES (?, ?)";
                    try (PreparedStatement stmtPeca = conn.prepareStatement(sqlPeca)) {
                        stmtPeca.setInt(1, peca.getIdPeca());
                        stmtPeca.setInt(2, idOrcamento);
                        stmtPeca.executeUpdate();
                    }
                }
            }
        }
    }

    // Método para atualizar orçamento
    public static void atualizarOrcamento(Orcamento orcamento) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE orcamento SET cpf = ?, total = ?, status_pagamento = ?, status_preparo = ? WHERE idorcamento = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, orcamento.getCpf());
            stmt.setBigDecimal(2, orcamento.getTotal());
            stmt.setBoolean(3, orcamento.isStatusPagamento());
            stmt.setBoolean(4, orcamento.isStatusPreparo());
            stmt.setInt(5, orcamento.getIdOrcamento());
            stmt.executeUpdate();

            // Remover entradas antigas e inserir novas para serviços e peças
            String sqlDeleteServicos = "DELETE FROM servicoorcamento WHERE idorcamento = ?";
            try (PreparedStatement stmtDeleteServicos = conn.prepareStatement(sqlDeleteServicos)) {
                stmtDeleteServicos.setInt(1, orcamento.getIdOrcamento());
                stmtDeleteServicos.executeUpdate();
            }

            for (Servico servico : orcamento.getServicos()) {
                String sqlServico = "INSERT INTO servicoorcamento (idservico, idorcamento) VALUES (?, ?)";
                try (PreparedStatement stmtServico = conn.prepareStatement(sqlServico)) {
                    stmtServico.setInt(1, servico.getIdServico());
                    stmtServico.setInt(2, orcamento.getIdOrcamento());
                    stmtServico.executeUpdate();
                }
            }

            String sqlDeletePecas = "DELETE FROM pecaorcamento WHERE idorcamento = ?";
            try (PreparedStatement stmtDeletePecas = conn.prepareStatement(sqlDeletePecas)) {
                stmtDeletePecas.setInt(1, orcamento.getIdOrcamento());
                stmtDeletePecas.executeUpdate();
            }

            for (Peca peca : orcamento.getPecas()) {
                String sqlPeca = "INSERT INTO pecaorcamento (idpeca, idorcamento) VALUES (?, ?)";
                try (PreparedStatement stmtPeca = conn.prepareStatement(sqlPeca)) {
                    stmtPeca.setInt(1, peca.getIdPeca());
                    stmtPeca.setInt(2, orcamento.getIdOrcamento());
                    stmtPeca.executeUpdate();
                }
            }
        }
    }

    // Método para deletar orçamento
    public static void deletarOrcamento(int idOrcamento) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM orcamento WHERE idorcamento = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idOrcamento);
            stmt.executeUpdate();
        }
    }

    // Método para buscar orçamento por ID
    public static Orcamento buscarOrcamento(int idOrcamento) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM orcamento WHERE idorcamento = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idOrcamento);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String cpf = rs.getString("cpf");
                BigDecimal total = rs.getBigDecimal("total");
                boolean statusPagamento = rs.getBoolean("status_pagamento");
                boolean statusPreparo = rs.getBoolean("status_preparo");

                // Buscar serviços associados
                List<Servico> servicos = new ArrayList<>();
                String sqlServicos = "SELECT s.* FROM servico s JOIN servicoorcamento so ON s.idservico = so.idservico WHERE so.idorcamento = ?";
                try (PreparedStatement stmtServicos = conn.prepareStatement(sqlServicos)) {
                    stmtServicos.setInt(1, idOrcamento);
                    ResultSet rsServicos = stmtServicos.executeQuery();
                    while (rsServicos.next()) {
                        Servico servico = new Servico(
                                rsServicos.getInt("idservico"),
                                rsServicos.getString("nomeservico"),
                                rsServicos.getBigDecimal("preco")
                        );
                        servicos.add(servico);
                    }
                }

                // Buscar peças associadas
                List<Peca> pecas = new ArrayList<>();
                String sqlPecas = "SELECT p.* FROM peca p JOIN pecaorcamento po ON p.idpeca = po.idpeca WHERE po.idorcamento = ?";
                try (PreparedStatement stmtPecas = conn.prepareStatement(sqlPecas)) {
                    stmtPecas.setInt(1, idOrcamento);
                    ResultSet rsPecas = stmtPecas.executeQuery();
                    while (rsPecas.next()) {
                        Peca peca = new Peca(
                                rsPecas.getInt("idpeca"),
                                rsPecas.getString("tipopeca"),
                                rsPecas.getString("nomepeca"),
                                rsPecas.getBigDecimal("preco")
                        );
                        pecas.add(peca);
                    }
                }

                return new Orcamento(idOrcamento, cpf, servicos, pecas, statusPagamento, statusPreparo);
            }
            return null;
        }
    }
    
    // Método para buscar todos os orçamentos
    public static List<Orcamento> buscarTodosOrcamentos() throws SQLException {
        List<Orcamento> orcamentos = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM orcamento";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idOrcamento = rs.getInt("idorcamento");
                String cpf = rs.getString("cpf");
                BigDecimal total = rs.getBigDecimal("total");
                boolean statusPagamento = rs.getBoolean("status_pagamento");
                boolean statusPreparo = rs.getBoolean("status_preparo");

                List<Servico> servicos = buscarServicosPorOrcamento(idOrcamento);
                List<Peca> pecas = buscarPecasPorOrcamento(idOrcamento);

                Orcamento orcamento = new Orcamento(idOrcamento, cpf, servicos, pecas, statusPagamento, statusPreparo);
                orcamentos.add(orcamento);
            }
        }
        return orcamentos;
    }
    
    
    // Método para buscar peças por orçamento
    public static List<Peca> buscarPecasPorOrcamento(int idOrcamento) throws SQLException {
        List<Peca> pecas = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT p.idpeca, p.tipopeca, p.nomepeca, p.preco FROM peca p JOIN pecaorcamento po ON p.idpeca = po.idpeca WHERE po.idorcamento = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idOrcamento);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Peca peca = new Peca(
                        rs.getInt("idpeca"),
                        rs.getString("tipopeca"),
                        rs.getString("nomepeca"),
                        rs.getBigDecimal("preco")
                    );
                    pecas.add(peca);
                }
            }
        }
        return pecas;
    }
    
    

    // Métodos de busca de serviços e peças associados a um orçamento
    public static List<Servico> buscarServicosPorOrcamento(int idOrcamento) throws SQLException {
        List<Servico> servicos = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT s.idservico, s.nomeservico, s.preco FROM servico s JOIN servicoorcamento so ON s.idservico = so.idservico WHERE so.idorcamento = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idOrcamento);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Servico servico = new Servico(
                        rs.getInt("idservico"),
                        rs.getString("nomeservico"),
                        rs.getBigDecimal("preco")
                    );
                    servicos.add(servico);
                }
            }
        }
        return servicos;
    }

  























}


























