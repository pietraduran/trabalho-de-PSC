import java.util.ArrayList;
import java.util.Scanner;

public class SistemaMercado {

    static ArrayList<Produto> listaProdutos = new ArrayList<>();
    static ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
    static ArrayList<Cliente> listaClientes = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        System.out.println("=================================");
        System.out.println("   SISTEMA DE GERENCIAMENTO     ");
        System.out.println("         DE MERCADO             ");
        System.out.println("=================================");

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Gerenciar Produtos");
            System.out.println("2. Gerenciar Funcionários");
            System.out.println("3. Gerenciar Clientes");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuProdutos();
                    break;
                case 2:
                    menuFuncionarios();
                    break;
                case 3:
                    menuClientes();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void menuProdutos() {
        int opcao;
        do {
            System.out.println("\n===== MENU PRODUTOS =====");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Remover Produto");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
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
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void cadastrarProduto() {
        System.out.println("\nCadastro de Produto");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Preço: R$");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade em estoque: ");
        int estoque = scanner.nextInt();
        scanner.nextLine();

        Produto novoProduto = new Produto(nome, categoria, preco, estoque);
        listaProdutos.add(novoProduto);

        System.out.println("Produto cadastrado com sucesso!");
    }

    static void listarProdutos() {
        System.out.println("\nLista de Produtos");

        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado");
            return;
        }

        for (int i = 0; i < listaProdutos.size(); i++) {
            System.out.println(i + " -> " + listaProdutos.get(i));
        }
    }

    static void atualizarProduto() {
        listarProdutos();

        if (listaProdutos.isEmpty()) return;

        System.out.print("\nInforme o índice do produto que deseja atualizar: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice < 0 || indice >= listaProdutos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Produto produto = listaProdutos.get(indice);

        System.out.print("Novo nome (" + produto.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) produto.setNome(nome);

        System.out.print("Nova categoria (" + produto.getCategoria() + "): ");
        String categoria = scanner.nextLine();
        if (!categoria.isEmpty()) produto.setCategoria(categoria);

        System.out.print("Novo preço (R$" + produto.getPreco() + "): ");
        String precoStr = scanner.nextLine();
        if (!precoStr.isEmpty()) produto.setPreco(Double.parseDouble(precoStr));

        System.out.print("Nova quantidade (" + produto.getQuantDeEstoque() + "): ");
        String estoqueStr = scanner.nextLine();
        if (!estoqueStr.isEmpty()) produto.setQuantDeEstoque(Integer.parseInt(estoqueStr));

        System.out.println("Produto atualizado com sucesso!");
    }

    static void removerProduto() {
        listarProdutos();

        if (listaProdutos.isEmpty()) return;

        System.out.print("\nInforme o índice do produto que deseja remover: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice < 0 || indice >= listaProdutos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Produto removido = listaProdutos.remove(indice);
        System.out.println("Produto removido: " + removido.getNome());
    }

    static void menuFuncionarios() {
        int opcao;
        do {
            System.out.println("\n===== MENU FUNCIONÁRIOS =====");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Listar Funcionários");
            System.out.println("3. Remover Funcionário");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    listarFuncionarios();
                    break;
                case 3:
                    removerFuncionario();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void cadastrarFuncionario() {
        System.out.println("\nCadastro de Funcionário");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Funcionario novoFuncionario = new Funcionario(nome, cpf);
        listaFuncionarios.add(novoFuncionario);

        System.out.println("Funcionário cadastrado com sucesso!");
        novoFuncionario.exibirDetalhes();
    }

    static void listarFuncionarios() {
        System.out.println("\nLista de Funcionários");

        if (listaFuncionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado");
            return;
        }

        for (int i = 0; i < listaFuncionarios.size(); i++) {
            System.out.println("\n[" + i + "]");
            listaFuncionarios.get(i).exibirDetalhes();
        }
    }

    static void removerFuncionario() {
        listarFuncionarios();

        if (listaFuncionarios.isEmpty()) return;

        System.out.print("\nInforme o índice do funcionário que deseja remover: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice < 0 || indice >= listaFuncionarios.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Funcionario removido = listaFuncionarios.remove(indice);
        System.out.println("Funcionário removido: " + removido.getNome());
    }

    static void menuClientes() {
        int opcao;
        do {
            System.out.println("\n===== MENU CLIENTES =====");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Remover Cliente");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    removerCliente();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void cadastrarCliente() {
        System.out.println("\nCadastro de Cliente");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Cliente novoCliente = new Cliente(nome, cpf);
        listaClientes.add(novoCliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    static void listarClientes() {
        System.out.println("\nLista de Clientes");

        if (listaClientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado");
            return;
        }

        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente c = listaClientes.get(i);
            System.out.println("[" + i + "] Nome: " + c.toString());
        }
    }

    static void removerCliente() {
        listarClientes();

        if (listaClientes.isEmpty()) return;

        System.out.print("\nInforme o índice do cliente que deseja remover: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice < 0 || indice >= listaClientes.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Cliente removido = listaClientes.remove(indice);
        System.out.println("Cliente removido com sucesso!");
    }
}