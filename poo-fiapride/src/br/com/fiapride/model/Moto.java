package br.com.fiapride.model;

/**
 * Classe Moto - filha de Veiculo, implementa Conduzivel.
 * Aula 6: Herança (extends Veiculo + uso de super e atributo com modificador adequado)
 * Aula 7: Polimorfismo (@Override em exibirInformacoes e calcularAutonomia)
 * Aula 9: Implementação de Conduzivel
 */
public class Moto extends Veiculo implements Conduzivel {

    private int cilindradas;
    private double tanqueEmLitros;
    private double kmPorLitro;
    private boolean ligada;
    private boolean aceitaPassageiro;

    // Aula 4: Construtor com super()
    public Moto(String placa, String modelo, String cor,
                int cilindradas, double tanqueEmLitros,
                double kmPorLitro, boolean aceitaPassageiro) {
        super(placa, modelo, cor);
        this.cilindradas = cilindradas;
        this.tanqueEmLitros = tanqueEmLitros;
        this.kmPorLitro = kmPorLitro;
        this.aceitaPassageiro = aceitaPassageiro;
        this.ligada = false;
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(int cilindradas) {
        if (cilindradas <= 0) {
            System.out.println("Erro: Cilindradas devem ser positivas.");
            return;
        }
        this.cilindradas = cilindradas;
    }

    public double getTanqueEmLitros() {
        return tanqueEmLitros;
    }

    public void setTanqueEmLitros(double tanqueEmLitros) {
        this.tanqueEmLitros = tanqueEmLitros;
    }

    public boolean isAceitaPassageiro() {
        return aceitaPassageiro;
    }

    public void setAceitaPassageiro(boolean aceitaPassageiro) {
        this.aceitaPassageiro = aceitaPassageiro;
    }

    // Aula 8: Implementação obrigatória do método abstrato
    @Override
    public double calcularAutonomia() {
        // Motos tendem a ter maior eficiência que carros
        return tanqueEmLitros * kmPorLitro;
    }

    // Aula 9: Interface Conduzivel
    @Override
    public void ligar() {
        if (ligada) {
            System.out.println("Moto " + getModelo() + " já está ligada.");
            return;
        }
        ligada = true;
        System.out.println("Moto " + getModelo() + " ligada! Brrrrm!");
    }

    @Override
    public void desligar() {
        if (!ligada) {
            System.out.println("Moto " + getModelo() + " já está desligada.");
            return;
        }
        ligada = false;
        System.out.println("Moto " + getModelo() + " desligada.");
    }

    @Override
    public boolean verificarAptidao() {
        if (!isDisponivel()) {
            System.out.println("Moto não disponível.");
            return false;
        }
        return true;
    }

    // Aula 7: Polimorfismo - sobrescrita do método da superclasse
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Cilindradas:       " + cilindradas + "cc");
        System.out.printf("Tanque:            %.1f L%n", tanqueEmLitros);
        System.out.printf("Autonomia:         %.1f km%n", calcularAutonomia());
        System.out.println("Aceita Passageiro: " + (aceitaPassageiro ? "Sim" : "Não"));
        System.out.println("==================================");
    }

    @Override
    public String toString() {
        return "Moto{modelo='" + getModelo() + "', placa='" + getPlaca() +
               "', cilindradas=" + cilindradas + "cc, autonomia=" + calcularAutonomia() + "km}";
    }
}
