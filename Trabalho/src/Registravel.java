import java.util.ArrayList;
import java.util.Scanner;

public class Registravel {

    static ArrayList<Produto> listaProdutos = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        System.out.println("Sistema de Cadastro dos Produtos");

        do {
            System.out.println("\n MENU: ");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Remover Produto");
            System.out.println("0. Sair");
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
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } 
        while (opcao != 0);
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

        System.out.println("Produto cadastrado com sucesso");
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
        int indic1 = scanner.nextInt();
        scanner.nextLine();

        if (indic1 < 0 || indic1 >= listaProdutos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Produto produto = listaProdutos.get(indic1);

        System.out.print("Novo nome (" + produto.getNome() + "): ");
        String nome = scanner.nextLine();
        produto.setNome(nome);

        System.out.print("Nova categoria (" + produto.getCategoria() + "): ");
        String categoria = scanner.nextLine();
        produto.setCategoria(categoria);

        System.out.print("Novo preço (R$" + produto.getPreco() + "): ");
        double preco = scanner.nextDouble();

        System.out.print("Nova quantidade (" + produto.getQuantDeEstoque() + "): ");
        int estoque = scanner.nextInt();
        scanner.nextLine(); 

        produto.setPreco(preco);
        produto.setQuantDeEstoque(estoque);

        System.out.println("Produto atualizado com sucesso!");
    }

    static void removerProduto() {
        listarProdutos();

        if (listaProdutos.isEmpty()) return;

        System.out.print("\nInforme o índice do produto que deseja remover: ");
        int indic2 = scanner.nextInt();
        scanner.nextLine(); 

        if (indic2 < 0 || indic2 >= listaProdutos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Produto removido = listaProdutos.remove(indic2);
        System.out.println("Produto removido: " + removido.getNome());
    }
}