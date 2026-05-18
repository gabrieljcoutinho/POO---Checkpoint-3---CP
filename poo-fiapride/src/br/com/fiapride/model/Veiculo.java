package br.com.fiapride.model;

/**
 * Classe Abstrata Veiculo - base da hierarquia de veículos do FiapRide.
 * Aula 6: Herança
 * Aula 8: Classes Abstratas
 *
 * Por que abstract?
 * No mundo real, ninguém entra numa concessionária e compra "um veículo genérico".
 * Sempre compramos um CARRO, uma MOTO, um CAMINHÃO.
 * Por isso, Veiculo é abstract: ela existe para definir o contrato e atributos
 * comuns, mas nunca deve ser instanciada diretamente.
 */
public abstract class Veiculo {

    // Aula 3: atributos privados (encapsulamento mantido na herança)
    private String placa;
    private String modelo;
    private String cor;
    private boolean disponivel;

    // Aula 4: Construtor da classe mãe
    public Veiculo(String placa, String modelo, String cor) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.disponivel = true;
    }

    // Getters e Setters

    public String getPlaca() {
        return placa;
    }

    // Aula 4: setPlaca é privado - a placa não deve ser alterada livremente
    // A atualização de placa deve ocorrer via método de negócio
    private void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Método público controlado para atualizar a placa.
     * Simula processo real do Detran (não se altera uma placa diretamente).
     */
    public void atualizarPlaca(String novaPlaca) {
        if (novaPlaca == null || novaPlaca.trim().isEmpty()) {
            System.out.println("Erro: Placa inválida.");
            return;
        }
        System.out.println("Placa atualizada de " + this.placa + " para " + novaPlaca);
        this.placa = novaPlaca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    // Aula 8: Método abstrato - toda subclasse OBRIGATORIAMENTE deve implementar
    /**
     * Calcula a autonomia do veículo em km.
     * Cada tipo de veículo calcula de forma diferente.
     */
    public abstract double calcularAutonomia();

    // Aula 7: Método que será sobrescrito (polimorfismo)
    /**
     * Exibe as informações do veículo.
     * Subclasses podem sobrescrever para adicionar mais detalhes.
     */
    public void exibirInformacoes() {
        System.out.println("===== Informações do Veículo =====");
        System.out.println("Modelo:     " + modelo);
        System.out.println("Placa:      " + placa);
        System.out.println("Cor:        " + cor);
        System.out.println("Disponível: " + (disponivel ? "Sim" : "Não"));
    }

    @Override
    public String toString() {
        return "Veiculo{modelo='" + modelo + "', placa='" + placa + "', disponivel=" + disponivel + "}";
    }
}
