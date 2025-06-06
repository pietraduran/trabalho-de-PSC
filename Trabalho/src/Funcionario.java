public class Funcionario extends Pessoa {

    public Funcionario(String nome, String cpf) {
        super(nome, cpf);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Funcionário:");
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
    }
}
