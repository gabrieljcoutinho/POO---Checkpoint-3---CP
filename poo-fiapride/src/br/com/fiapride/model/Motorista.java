package br.com.fiapride.model;

/**
 * Classe Motorista - representa um motorista parceiro do FiapRide.
 * Complementa o sistema com mais uma entidade de negócio.
 */
public class Motorista {

    private String nome;
    private String cpf;
    private String cnh;
    private double avaliacaoMedia;
    private int totalViagens;
    private Veiculo veiculo; // Associação: Motorista TEM-UM Veiculo

    public Motorista(String nome, String cpf, String cnh, Veiculo veiculo) {
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.veiculo = veiculo;
        this.avaliacaoMedia = 5.0;
        this.totalViagens = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Erro: Nome inválido.");
            return;
        }
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public double getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    public int getTotalViagens() {
        return totalViagens;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * Registra avaliação recebida após uma viagem.
     * Atualiza a média e incrementa o total de viagens.
     */
    public void receberAvaliacao(double nota) {
        if (nota < 1 || nota > 5) {
            System.out.println("Avaliação deve ser entre 1 e 5.");
            return;
        }
        // Recalcula a média ponderada
        avaliacaoMedia = ((avaliacaoMedia * totalViagens) + nota) / (totalViagens + 1);
        totalViagens++;
        System.out.printf("Avaliação registrada! Nova média: %.2f (%d viagens)%n",
                          avaliacaoMedia, totalViagens);
    }

    public void exibirDados() {
        System.out.println("===== Dados do Motorista =====");
        System.out.println("Nome:          " + nome);
        System.out.println("CPF:           " + cpf);
        System.out.println("CNH:           " + cnh);
        System.out.printf("Avaliação:     %.2f/5.0%n", avaliacaoMedia);
        System.out.println("Total Viagens: " + totalViagens);
        System.out.println("Veículo:       " + (veiculo != null ? veiculo.getModelo() : "Sem veículo"));
        System.out.println("==============================");
    }

    @Override
    public String toString() {
        return "Motorista{nome='" + nome + "', avaliacao=" + String.format("%.2f", avaliacaoMedia) + "}";
    }
}
