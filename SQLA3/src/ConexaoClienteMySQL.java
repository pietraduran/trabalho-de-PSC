import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ConexaoClienteMySQL {

    static class Cliente {
        private String nome;
        private String cpf;

        public Cliente(String nome, String cpf) {
            this.nome = nome;
            this.cpf = cpf;
        }

        public String getNome() {
            return nome;
        }

        public String getCpf() {
            return cpf;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        @Override
        public String toString() {
            return "Cliente{nome='" + nome + "', cpf='" + cpf + "'}";
        }
    }

    static Scanner scanner = new Scanner(System.in);
    static final String URL = "jdbc:mysql://localhost:3306/PROJETOA3";
    static final String USUARIO = "root";
    static final String SENHA = "root";

    public static void main(String[] args) {
        System.out.println("Sistema de Cadastro de Clientes");
        int opcao;

        do {
            System.out.println("\n MENU: ");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Remover Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Erro: Opção inválida!");
                scanner.nextLine();
                opcao = -1;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    atualizarCliente();
                    break;
                case 4:
                    removerCliente();
                    break;
                case 0:
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
        
        scanner.close();
    }

    static void cadastrarCliente() {
        System.out.println("\nCadastro de Cliente");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF (11 dígitos): ");
        String cpf = scanner.nextLine();

        if (cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            System.out.println("Erro: CPF deve ter exatamente 11 dígitos numéricos!");
            return;
        }

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "INSERT INTO Cliente (nome, cpf) VALUES (?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cpf);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente cadastrado com sucesso!");
            } else {
                System.out.println("Nenhum dado foi inserido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void listarClientes() {
        System.out.println("\nLista de Clientes");

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "SELECT * FROM Cliente";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");

                Cliente cliente = new Cliente(nome, cpf);
                System.out.println(id + " -> " + cliente);
            }

            if (!hasResults) {
                System.out.println("Nenhum cliente cadastrado");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void atualizarCliente() {
        listarClientes();

        System.out.print("\nInforme o ID do cliente que deseja atualizar: ");
        int id;
        try {
            id = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Erro: ID inválido!");
            scanner.nextLine();
            return;
        }
        scanner.nextLine();

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String checkSql = "SELECT nome, cpf FROM Cliente WHERE id = ?";
            PreparedStatement checkStmt = conexao.prepareStatement(checkSql);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Cliente com ID " + id + " não encontrado.");
                return;
            }

            String nomeAtual = rs.getString("nome");
            String cpfAtual = rs.getString("cpf");

            System.out.print("Novo nome (" + nomeAtual + "): ");
            String nome = scanner.nextLine();
            if (nome.trim().isEmpty()) nome = nomeAtual;

            System.out.print("Novo CPF (" + cpfAtual + "): ");
            String cpf = scanner.nextLine();
            if (cpf.trim().isEmpty()) cpf = cpfAtual;

            if (cpf.length() != 11 || !cpf.matches("\\d{11}")) {
                System.out.println("Erro: CPF deve ter exatamente 11 dígitos numéricos!");
                return;
            }

            String sql = "UPDATE Cliente SET nome = ?, cpf = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setInt(3, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Falha ao atualizar o cliente.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void removerCliente() {
        listarClientes();

        System.out.print("\nInforme o ID do cliente que deseja remover: ");
        int id;
        try {
            id = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Erro: ID inválido!");
            scanner.nextLine();
            return;
        }
        scanner.nextLine();

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String checkSql = "SELECT nome FROM Cliente WHERE id = ?";
            PreparedStatement checkStmt = conexao.prepareStatement(checkSql);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Cliente com ID " + id + " não encontrado.");
                return;
            }

            String nomeRemovido = rs.getString("nome");

            String sql = "DELETE FROM Cliente WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente removido: " + nomeRemovido);
            } else {
                System.out.println("Falha ao remover o cliente.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}