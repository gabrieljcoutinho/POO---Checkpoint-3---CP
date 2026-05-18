package br.com.fiapride.model;

/**
 * Classe Passageiro - representa um usuário do sistema FiapRide.
 * Aula 1: Classes e Objetos
 * Aula 2: Métodos
 * Aula 3: Encapsulamento
 * Aula 4: Construtores
 */
public class Passageiro {

    // Aula 3: Encapsulamento - atributos privados
    private String nome;
    private String cpf;
    private double saldo;
    private String email;

    // Aula 4: Construtor padrão
    public Passageiro() {
        this.nome = "Sem nome";
        this.cpf = "000.000.000-00";
        this.saldo = 0.0;
        this.email = "sem@email.com";
    }

    // Aula 4: Construtor customizado com this
    public Passageiro(String nome, String cpf, double saldo, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.saldo = saldo;
        this.email = email;
    }

    // Aula 3: Getters e Setters com validação

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        // Validação de dados no setter
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Erro: Nome não pode ser vazio.");
            return;
        }
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            System.out.println("Erro: CPF não pode ser vazio.");
            return;
        }
        this.cpf = cpf;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        if (saldo < 0) {
            System.out.println("Erro: Saldo não pode ser negativo.");
            return;
        }
        this.saldo = saldo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            System.out.println("Erro: E-mail inválido.");
            return;
        }
        this.email = email;
    }

    // Aula 2: Métodos com parâmetros e retorno

    /**
     * Adiciona saldo à conta do passageiro.
     * Retorna true se a operação foi bem-sucedida.
     */
    public boolean adicionarSaldo(double valor) {
        if (valor <= 0) {
            System.out.println("Erro: Valor para adicionar deve ser positivo.");
            return false;
        }
        this.saldo += valor;
        System.out.println("Saldo adicionado com sucesso! Novo saldo: R$ " + this.saldo);
        return true;
    }

    /**
     * Debita saldo do passageiro.
     * Retorna true se houver saldo suficiente.
     */
    public boolean debitarSaldo(double valor) {
        if (valor <= 0) {
            System.out.println("Erro: Valor para debitar deve ser positivo.");
            return false;
        }
        if (this.saldo < valor) {
            System.out.println("Erro: Saldo insuficiente. Saldo atual: R$ " + this.saldo);
            return false;
        }
        this.saldo -= valor;
        System.out.println("Débito realizado. Saldo restante: R$ " + this.saldo);
        return true;
    }

    /**
     * Exibe os dados do passageiro de forma formatada.
     */
    public void exibirDados() {
        System.out.println("===== Dados do Passageiro =====");
        System.out.println("Nome:  " + nome);
        System.out.println("CPF:   " + cpf);
        System.out.println("Email: " + email);
        System.out.printf("Saldo: R$ %.2f%n", saldo);
        System.out.println("================================");
    }

    @Override
    public String toString() {
        return "Passageiro{nome='" + nome + "', cpf='" + cpf + "', saldo=" + saldo + "}";
    }
}
