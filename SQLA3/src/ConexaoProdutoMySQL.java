import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ConexaoProdutoMySQL {

    static class Produto {
        private String Nome, Categoria;
        private double Preco;
        private int QuantDeEstoque;

        public Produto(String nome, String categoria, double preco, int quantDeEstoque) {
            this.Nome = nome;
            this.Categoria = categoria;
            this.Preco = preco;
            this.QuantDeEstoque = quantDeEstoque;
        }

        public String getNome() {
            return Nome;
        }

        public void setNome(String nome) {
            this.Nome = nome;
        }

        public String getCategoria() {
            return Categoria;
        }

        public void setCategoria(String categoria) {
            this.Categoria = categoria;
        }

        public double getPreco() {
            return Preco;
        }

        public void setPreco(double preco) {
            this.Preco = preco;
        }

        public int getQuantDeEstoque() {
            return QuantDeEstoque;
        }

        public void setQuantDeEstoque(int quantDeEstoque) {
            this.QuantDeEstoque = quantDeEstoque;
        }

        @Override
        public String toString() {
            return "Produto{nome='" + Nome + "', categoria='" + Categoria + "', preco=R$" + Preco + ", quantDeEstoque=" + QuantDeEstoque + "}";
        }
    }

    static Scanner scanner = new Scanner(System.in);
    static final String URL = "jdbc:mysql://localhost:3306/PROJETOA3";
    static final String USUARIO = "root";
    static final String SENHA = "root";

    public static void main(String[] args) {
        System.out.println("Sistema de Cadastro de Produtos");
        int opcao;

        do {
            System.out.println("\n MENU: ");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Remover Produto");
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
                    cadastrarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    atualizarProduto();
                    break;
                case 4:
                    removerProduto();
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

    static void cadastrarProduto() {
        System.out.println("\nCadastro de Produto");

        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade em estoque: ");
        int quantDeEstoque = scanner.nextInt();
        scanner.nextLine();

        if (preco < 0) {
            System.out.println("Erro: O preço não pode ser negativo!");
            return;
        }
        if (quantDeEstoque < 0) {
            System.out.println("Erro: A quantidade em estoque não pode ser negativa!");
            return;
        }

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "INSERT INTO Produto (nome, categoria, preco, quantDeEstoque) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, categoria);
            stmt.setDouble(3, preco);
            stmt.setInt(4, quantDeEstoque);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto cadastrado com sucesso!");
            } else {
                System.out.println("Nenhum dado foi inserido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void listarProdutos() {
        System.out.println("\nLista de Produtos");

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "SELECT * FROM Produto";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String categoria = rs.getString("categoria");
                double preco = rs.getDouble("preco");
                int quantDeEstoque = rs.getInt("quantDeEstoque");

                Produto produto = new Produto(nome, categoria, preco, quantDeEstoque);
                System.out.println(id + " -> " + produto);
            }

            if (!hasResults) {
                System.out.println("Nenhum produto cadastrado");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void atualizarProduto() {
        listarProdutos();

        System.out.print("\nInforme o ID do produto que deseja atualizar: ");
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
            String checkSql = "SELECT nome, categoria, preco, quantDeEstoque FROM Produto WHERE id = ?";
            PreparedStatement checkStmt = conexao.prepareStatement(checkSql);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Produto com ID " + id + " não encontrado.");
                return;
            }

            String nomeAtual = rs.getString("nome");
            String categoriaAtual = rs.getString("categoria");
            double precoAtual = rs.getDouble("preco");
            int estoqueAtual = rs.getInt("quantDeEstoque");

            System.out.print("Novo nome (" + nomeAtual + "): ");
            String nome = scanner.nextLine();
            if (nome.trim().isEmpty()) nome = nomeAtual;

            System.out.print("Nova categoria (" + categoriaAtual + "): ");
            String categoria = scanner.nextLine();
            if (categoria.trim().isEmpty()) categoria = categoriaAtual;

            System.out.print("Novo preço (" + precoAtual + "): ");
            String precoStr = scanner.nextLine();
            double preco = precoStr.trim().isEmpty() ? precoAtual : Double.parseDouble(precoStr);

            System.out.print("Nova quantidade (" + estoqueAtual + "): ");
            String estoqueStr = scanner.nextLine();
            int estoque = estoqueStr.trim().isEmpty() ? estoqueAtual : Integer.parseInt(estoqueStr);

            if (preco < 0) {
                System.out.println("Erro: O preço não pode ser negativo!");
                return;
            }
            if (estoque < 0) {
                System.out.println("Erro: A quantidade em estoque não pode ser negativa!");
                return;
            }

            String sql = "UPDATE Produto SET nome = ?, categoria = ?, preco = ?, quantDeEstoque = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, categoria);
            stmt.setDouble(3, preco);
            stmt.setInt(4, estoque);
            stmt.setInt(5, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Falha ao atualizar o produto.");
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void removerProduto() {
        listarProdutos();

        System.out.print("\nInforme o ID do produto que deseja remover: ");
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
            String checkSql = "SELECT nome FROM Produto WHERE id = ?";
            PreparedStatement checkStmt = conexao.prepareStatement(checkSql);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Produto com ID " + id + " não encontrado.");
                return;
            }

            String nomeRemovido = rs.getString("nome");

            String sql = "DELETE FROM Produto WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto removido: " + nomeRemovido);
            } else {
                System.out.println("Falha ao remover o produto.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover produto: " + e.getMessage());
            e.printStackTrace();
        }
    }
}