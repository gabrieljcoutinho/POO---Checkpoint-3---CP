package br.com.fiapride.model;

/**
 * Classe Carro - filha de Veiculo, implementa Conduzivel.
 * Aula 6: Herança (extends Veiculo)
 * Aula 7: Polimorfismo (@Override em exibirInformacoes)
 * Aula 8: Implementação obrigatória de calcularAutonomia (método abstrato)
 * Aula 9: Implementação da interface Conduzivel
 */
public class Carro extends Veiculo implements Conduzivel {

    private int numeroDePorras;
    private double tanqueEmLitros;
    private double kmPorLitro;
    private boolean ligado;
    private String tipoCombustivel;

    // Aula 4: Construtor usando super() para chamar o construtor da mãe
    public Carro(String placa, String modelo, String cor,
                 int numeroDePorras, double tanqueEmLitros,
                 double kmPorLitro, String tipoCombustivel) {
        super(placa, modelo, cor); // Aula 6: chamada ao construtor da mãe
        this.numeroDePorras = numeroDePorras;
        this.tanqueEmLitros = tanqueEmLitros;
        this.kmPorLitro = kmPorLitro;
        this.tipoCombustivel = tipoCombustivel;
        this.ligado = false;
    }

    // Getters e Setters específicos de Carro

    public int getNumeroDePorras() {
        return numeroDePorras;
    }

    public void setNumeroDePorras(int numeroDePorras) {
        if (numeroDePorras != 2 && numeroDePorras != 4) {
            System.out.println("Erro: Carro deve ter 2 ou 4 portas.");
            return;
        }
        this.numeroDePorras = numeroDePorras;
    }

    public double getTanqueEmLitros() {
        return tanqueEmLitros;
    }

    public void setTanqueEmLitros(double tanqueEmLitros) {
        if (tanqueEmLitros <= 0) {
            System.out.println("Erro: Tanque deve ter capacidade positiva.");
            return;
        }
        this.tanqueEmLitros = tanqueEmLitros;
    }

    public double getKmPorLitro() {
        return kmPorLitro;
    }

    public void setKmPorLitro(double kmPorLitro) {
        this.kmPorLitro = kmPorLitro;
    }

    public boolean isLigado() {
        return ligado;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    // Aula 8: Implementação OBRIGATÓRIA do método abstrato da mãe
    @Override
    public double calcularAutonomia() {
        return tanqueEmLitros * kmPorLitro;
    }

    // Aula 9: Implementações da interface Conduzivel

    @Override
    public void ligar() {
        if (ligado) {
            System.out.println("Carro " + getModelo() + " já está ligado.");
            return;
        }
        ligado = true;
        System.out.println("Carro " + getModelo() + " ligado! Vrum vrum!");
    }

    @Override
    public void desligar() {
        if (!ligado) {
            System.out.println("Carro " + getModelo() + " já está desligado.");
            return;
        }
        ligado = false;
        System.out.println("Carro " + getModelo() + " desligado.");
    }

    @Override
    public boolean verificarAptidao() {
        if (!isDisponivel()) {
            System.out.println("Carro não disponível para viagem.");
            return false;
        }
        if (tanqueEmLitros < 5) {
            System.out.println("Combustível abaixo do mínimo. Reabastecimento necessário.");
            return false;
        }
        return true;
    }

    // Aula 7: Sobrescrita do método da mãe (polimorfismo)
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes(); // Aula 6: uso de super() para reaproveitar lógica da mãe
        System.out.println("Portas:       " + numeroDePorras);
        System.out.println("Combustível:  " + tipoCombustivel);
        System.out.printf("Tanque:       %.1f L%n", tanqueEmLitros);
        System.out.printf("Autonomia:    %.1f km%n", calcularAutonomia());
        System.out.println("Ligado:       " + (ligado ? "Sim" : "Não"));
        System.out.println("==================================");
    }

    @Override
    public String toString() {
        return "Carro{modelo='" + getModelo() + "', placa='" + getPlaca() +
               "', portas=" + numeroDePorras + ", autonomia=" + calcularAutonomia() + "km}";
    }
}
