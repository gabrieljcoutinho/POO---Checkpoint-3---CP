package br.com.fiapride.model;

/**
 * Classe Viagem - representa uma corrida no FiapRide.
 * Aula 5: Associação (TEM-UM - Viagem TEM-UM Passageiro e TEM-UM Veiculo)
 *
 * Por que precisamos do objeto inteiro e não só o nome?
 * Porque quando a viagem TERMINA, o sistema precisa:
 * - Debitar o saldo do Passageiro (precisa do objeto com método debitarSaldo)
 * - Marcar o Veiculo como disponível novamente (precisa do objeto com setDisponivel)
 * - Se tivéssemos só o nome, não conseguiríamos fazer essas operações.
 * A String é apenas um dado; o objeto é uma entidade com comportamento.
 */
public class Viagem {

    private String id;
    private Passageiro passageiroSolicitante; // Aula 5: Associação (TEM-UM)
    private Veiculo veiculoDesignado;         // Aula 5: Associação (TEM-UM)
    private String origem;
    private String destino;
    private double distanciaKm;
    private double valorTarifa;
    private String status; // "AGUARDANDO", "EM_ANDAMENTO", "CONCLUIDA", "CANCELADA"

    // Aula 4: Construtor - recebe objetos inteiros, não apenas Strings
    public Viagem(String id, Passageiro passageiroSolicitante,
                  Veiculo veiculoDesignado, String origem,
                  String destino, double distanciaKm) {
        this.id = id;
        this.passageiroSolicitante = passageiroSolicitante;
        this.veiculoDesignado = veiculoDesignado;
        this.origem = origem;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.valorTarifa = calcularTarifa();
        this.status = "AGUARDANDO";
    }

    // Getters

    public String getId() {
        return id;
    }

    public Passageiro getPassageiroSolicitante() {
        return passageiroSolicitante;
    }

    public Veiculo getVeiculoDesignado() {
        return veiculoDesignado;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public double getValorTarifa() {
        return valorTarifa;
    }

    public String getStatus() {
        return status;
    }

    // Aula 2: Métodos de negócio

    /**
     * Calcula o valor da tarifa com base na distância.
     * Taxa base: R$5,00 + R$2,50 por km.
     */
    public double calcularTarifa() {
        double taxaBase = 5.00;
        double taxaPorKm = 2.50;
        return taxaBase + (distanciaKm * taxaPorKm);
    }

    /**
     * Inicia a viagem.
     * - Verifica se o passageiro tem saldo suficiente
     * - Marca o veículo como indisponível
     * - Muda o status para EM_ANDAMENTO
     */
    public boolean iniciarViagem() {
        if (!status.equals("AGUARDANDO")) {
            System.out.println("Viagem não pode ser iniciada. Status atual: " + status);
            return false;
        }

        if (passageiroSolicitante.getSaldo() < valorTarifa) {
            System.out.println("Saldo insuficiente! Tarifa: R$" + String.format("%.2f", valorTarifa) +
                               " | Saldo: R$" + String.format("%.2f", passageiroSolicitante.getSaldo()));
            status = "CANCELADA";
            return false;
        }

        veiculoDesignado.setDisponivel(false);
        status = "EM_ANDAMENTO";
        System.out.println("Viagem " + id + " iniciada! " +
                           passageiroSolicitante.getNome() + " indo de " +
                           origem + " → " + destino);
        return true;
    }

    /**
     * Conclui a viagem.
     * - Debita o saldo do passageiro
     * - Marca o veículo como disponível novamente
     * - Muda o status para CONCLUIDA
     */
    public void concluirViagem() {
        if (!status.equals("EM_ANDAMENTO")) {
            System.out.println("Viagem não está em andamento. Status: " + status);
            return;
        }

        // Debita o saldo do passageiro (só possível por ter o OBJETO inteiro)
        passageiroSolicitante.debitarSaldo(valorTarifa);

        // Libera o veículo (só possível por ter o OBJETO inteiro)
        veiculoDesignado.setDisponivel(true);

        status = "CONCLUIDA";
        System.out.println("Viagem " + id + " concluída! Obrigado, " +
                           passageiroSolicitante.getNome() + "!");
        exibirResumo();
    }

    /**
     * Cancela a viagem.
     */
    public void cancelarViagem() {
        if (status.equals("CONCLUIDA")) {
            System.out.println("Não é possível cancelar uma viagem já concluída.");
            return;
        }
        if (!status.equals("EM_ANDAMENTO") && !status.equals("AGUARDANDO")) {
            System.out.println("Viagem já está cancelada.");
            return;
        }
        veiculoDesignado.setDisponivel(true);
        status = "CANCELADA";
        System.out.println("Viagem " + id + " cancelada.");
    }

    /**
     * Exibe o resumo da viagem.
     */
    public void exibirResumo() {
        System.out.println("===== Resumo da Viagem " + id + " =====");
        System.out.println("Passageiro: " + passageiroSolicitante.getNome());
        System.out.println("Veículo:    " + veiculoDesignado.getModelo() +
                           " (" + veiculoDesignado.getPlaca() + ")");
        System.out.println("Origem:     " + origem);
        System.out.println("Destino:    " + destino);
        System.out.printf("Distância:  %.1f km%n", distanciaKm);
        System.out.printf("Tarifa:     R$ %.2f%n", valorTarifa);
        System.out.println("Status:     " + status);
        System.out.println("===========================================");
    }

    @Override
    public String toString() {
        return "Viagem{id='" + id + "', passageiro='" + passageiroSolicitante.getNome() +
               "', status='" + status + "', tarifa=R$" + String.format("%.2f", valorTarifa) + "}";
    }
}
