package br.com.fiapride.model;

/**
 * Interface Conduzivel - define o contrato para veículos que podem ser conduzidos.
 * Aula 9: Interfaces
 *
 * Por que Interface e não herança múltipla?
 * Java permite herdar de apenas UMA classe mãe (herança simples),
 * mas permite implementar MÚLTIPLAS interfaces.
 *
 * Exemplo: CarroEletrico poderia herdar de Veiculo E implementar
 * Conduzivel E Recarregavel ao mesmo tempo.
 * Se Conduzivel fosse uma classe, teríamos o "problema do diamante"
 * (conflito de métodos quando duas mães têm o mesmo método).
 * A interface resolve isso: é apenas um CONTRATO, sem implementação,
 * então não há conflito.
 */
public interface Conduzivel {

    // Constante de interface (implicitamente public static final)
    double VELOCIDADE_MAXIMA_PERMITIDA = 120.0; // km/h
    int TEMPO_MINIMO_DESCANSO = 4; // horas entre viagens longas

    /**
     * Liga o veículo para uma viagem.
     */
    void ligar();

    /**
     * Desliga o veículo ao término da viagem.
     */
    void desligar();

    /**
     * Verifica se o veículo está apto para realizar a viagem.
     * @return true se estiver apto
     */
    boolean verificarAptidao();
}
