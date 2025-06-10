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