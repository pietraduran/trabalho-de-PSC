public class Produto {
    private String Nome, Categoria;
    private double Preco;
    private int QuantDeEstoque;
    
    public Produto(String nome, String categoria,double preco, int quantDeEstoque){
        this.Nome = nome;
        this.Categoria = categoria;
        this.Preco = preco;
        this.QuantDeEstoque = quantDeEstoque;
    }

    public String getNome(){
        return Nome;
    }
    public void setNome(String nome){
        this.Nome = nome;
    }

    public String getCategoria(){
        return Categoria;
    }
    public void setCategoria(String categoria){
        this.Categoria = categoria;
    }

    public double getPreco(){
        return Preco;
    }
    public void setPreco(double preco){
        this.Preco = preco;
    }

    public int getQuantDeEstoque(){
        return QuantDeEstoque;
    }
    public void setQuantDeEstoque(int quantDeEstoque){
        this.QuantDeEstoque = quantDeEstoque;
    }

    public String toString(){
        return "Produto -> " + "nome =" + Nome + " , categoria =" + Categoria + " , preco = R$" + Preco + " , quantDeEstoque = " + QuantDeEstoque + " .";
    }
}
public void armazenamentoProduto(){

    private ArrayList<Produto> produtos;
    private Scanner scanner = new Scanner(System.in);
}

public void adicionarProduto() {
    System.out.print("=========== ADICIONAR PRODUTO ===========");
    
    System.out.println("Digite o nome do produto ");
    String nome = scanner.nextLine();
    
    System.out.println("Digite a categoria do produto ");
    String categoria = scanner.nextLine();
}
    public void consultarProduto() {
        System.out.println("Digite o nome para consulta");
        String Nome = getNome();

       if (Nome != null) {
            System.out.println("Produto já cadastrado.");

        } else {
        System.out.println("Produto inexistente.");
       }
    }
