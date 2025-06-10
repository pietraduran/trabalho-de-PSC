public class Funcionario {
    private String nome;
    private String cpf;
    
    public Funcionario(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    
    public void exibirDetalhes() { 
        
        System.out.println("Funcionário:");
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
    }
    

    
    public String getNome() {
        return nome;
    }
    
    public String getCpf() {
        return cpf;
    }
}