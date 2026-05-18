package br.com.fiapride.main;

import br.com.fiapride.model.*;

/**
 * Classe principal do FiapRide - demonstra todos os conceitos das aulas 1 a 9.
 *
 * Para compilar e rodar:
 *   javac -d out src/br/com/fiapride/model/*.java src/br/com/fiapride/main/*.java
 *   java -cp out br.com.fiapride.main.FiapRideApp
 */
public class FiapRideApp {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         BEM-VINDO AO FIAAPRIDE       ║");
        System.out.println("║   Sistema de Mobilidade Urbana POO   ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        // ================================================
        // AULA 1 - Classes e Objetos
        // ================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(">>> AULA 1: Classes e Objetos");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Passageiro passageiro1 = new Passageiro("Ana Silva", "123.456.789-00", 150.0, "ana@email.com");
        Passageiro passageiro2 = new Passageiro("Bruno Costa", "987.654.321-00", 50.0, "bruno@email.com");

        passageiro1.exibirDados();
        passageiro2.exibirDados();

        // ================================================
        // AULA 2 - Métodos com parâmetros e retorno
        // ================================================
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(">>> AULA 2: Métodos");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        System.out.println("Adicionando saldo para Ana:");
        passageiro1.adicionarSaldo(100.0);

        System.out.println("\nTentando adicionar valor inválido:");
        passageiro1.adicionarSaldo(-50.0);

        System.out.println("\nDebitando saldo de Bruno:");
        passageiro2.debitarSaldo(30.0);

        System.out.println("\nTentando debitar mais do que Bruno tem:");
        passageiro2.debitarSaldo(200.0);

        // ================================================
        // AULA 3 - Encapsulamento
        // ================================================
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(">>> AULA 3: Encapsulamento");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        System.out.println("Testando validação dos setters:");
        passageiro1.setNome("");          // inválido
        passageiro1.setEmail("invalido"); // inválido
        passageiro1.setSaldo(-100.0);     // inválido
        System.out.println("Ana após tentativas inválidas: " + passageiro1.getNome() +
                           " | saldo: R$" + passageiro1.getSaldo());

        // ================================================
        // AULA 4 - Construtores
        // ================================================
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(">>> AULA 4: Construtores");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // Construtor padrão
        Passageiro passagemPadrao = new Passageiro();
        System.out.println("Passageiro criado com construtor padrão: " + passagemPadrao);

        // Construtor customizado
        Carro carro1 = new Carro("ABC-1234", "Toyota Corolla", "Prata",
                                  4, 50.0, 12.5, "Flex");
        Carro carro2 = new Carro("XYZ-5678", "Honda Civic", "Preto",
                                  4, 47.0, 13.0, "Gasolina");
        Moto moto1 = new Moto("MOT-9999", "Honda CB 500", "Azul",
                               500, 17.5, 22.0, false);

        System.out.println("Veículos criados:");
        System.out.println(carro1);
        System.out.println(carro2);
        System.out.println(moto1);

        // ================================================
        // AULA 5 - Associação
        // ================================================
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(">>> AULA 5: Associação (TEM-UM)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // Viagem associa Passageiro + Veiculo
        Viagem viagem1 = new Viagem("VGM-001", passageiro1, carro1,
                                    "Paulista, São Paulo", "Aeroporto de Guarulhos", 35.0);

        System.out.println("Viagem criada: " + viagem1);
        System.out.printf("Tarifa calculada: R$ %.2f%n", viagem1.calcularTarifa());

        viagem1.iniciarViagem();
        viagem1.concluirViagem();

        // Motorista também tem associação com Veiculo
        Motorista motorista1 = new Motorista("Carlos Oliveira", "111.222.333-44", "CNH-123456", carro2);
        motorista1.exibirDados();
        motorista1.receberAvaliacao(5.0);
        motorista1.receberAvaliacao(4.5);

        // ================================================
        // AULA 6 - Herança
        // ================================================
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(">>> AULA 6: Herança");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        System.out.println("Carro HERDA de Veiculo, acessa atributos via super:");
        carro1.exibirInformacoes();

        System.out.println("Testando atualizarPlaca (método de negócio controlado):");
        // carro1.setPlaca("ZZZ-0000"); // Isso não compilaria - setPlaca é private!
        carro1.atualizarPlaca("ABC-9999"); // Este é o caminho correto

        System.out.println("\nMoto também herda de Veiculo:");
        moto1.exibirInformacoes();

        // ================================================
        // AULA 7 - Polimorfismo
        // ================================================
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(">>> AULA 7: Polimorfismo");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // Array de Veiculo (tipo genérico) - polimorfismo em ação!
        Veiculo[] frota = {carro1, carro2, moto1};

        System.out.println("Calculando autonomia da frota (polimorfismo):");
        for (Veiculo veiculo : frota) {
            // calcularAutonomia() é chamado de forma polimórfica
            // Java decide em TEMPO DE EXECUÇÃO qual implementação usar
            System.out.printf("  %-20s → Autonomia: %.1f km%n",
                              veiculo.getModelo(), veiculo.calcularAutonomia());
        }

        System.out.println("\nExibindo informações polimorficamente:");
        for (Veiculo veiculo : frota) {
            veiculo.exibirInformacoes(); // Cada um executa sua própria versão
        }

        // ================================================
        // AULA 8 - Classes Abstratas
        // ================================================
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(">>> AULA 8: Classes Abstratas");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // new Veiculo("X","X","X") → ERRO DE COMPILAÇÃO! Veiculo é abstract.
        // Isso é intencional: não existe "veículo genérico" no mundo real.
        System.out.println("Veiculo é abstract: não pode ser instanciado diretamente.");
        System.out.println("Carro e Moto IMPLEMENTAM calcularAutonomia() obrigatoriamente.");
        System.out.println("Carro autonomia: " + carro1.calcularAutonomia() + " km");
        System.out.println("Moto autonomia:  " + moto1.calcularAutonomia() + " km");

        // ================================================
        // AULA 9 - Interfaces
        // ================================================
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(">>> AULA 9: Interfaces (Conduzivel)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        System.out.println("Constante da interface: VELOCIDADE_MAXIMA = " +
                           Conduzivel.VELOCIDADE_MAXIMA_PERMITIDA + " km/h");

        // Usando a interface como tipo (polimorfismo de interface)
        Conduzivel[] veiculosConduzíveis = {carro1, carro2, moto1};

        System.out.println("\nLigando todos os veículos conduzíveis:");
        for (Conduzivel c : veiculosConduzíveis) {
            c.ligar();
        }

        System.out.println("\nVerificando aptidão para viagem:");
        for (Conduzivel c : veiculosConduzíveis) {
            boolean apto = c.verificarAptidao();
            // Cast para pegar o modelo (Conduzivel não tem getModelo)
            if (c instanceof Veiculo) {
                System.out.println("  " + ((Veiculo) c).getModelo() + ": " + (apto ? "✔ Apto" : "✗ Inapto"));
            }
        }

        System.out.println("\nDesligando todos:");
        for (Conduzivel c : veiculosConduzíveis) {
            c.desligar();
        }

        // ================================================
        // CENÁRIO COMPLETO - Simulação real
        // ================================================
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║        SIMULAÇÃO COMPLETA            ║");
        System.out.println("╚══════════════════════════════════════╝");

        Passageiro joao = new Passageiro("João Pereira", "555.666.777-88", 80.0, "joao@email.com");
        Carro carroSimulado = new Carro("SIM-0001", "VW Polo", "Branco", 4, 40.0, 11.0, "Flex");

        System.out.println("Passageiro: " + joao.getNome() + " | Saldo: R$" + joao.getSaldo());
        System.out.println("Veículo disponível: " + carroSimulado.getModelo());

        Viagem viagemSimulada = new Viagem("VGM-999", joao, carroSimulado,
                                           "FIAP Paulista", "Shopping Ibirapuera", 8.5);

        System.out.println("\nIniciando viagem simulada...");
        carroSimulado.ligar();
        viagemSimulada.iniciarViagem();

        System.out.println("\nConcluindo viagem...");
        carroSimulado.desligar();
        viagemSimulada.concluirViagem();

        System.out.println("\n>>> Saldo restante de " + joao.getNome() + ": R$" + joao.getSaldo());

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║    FiapRide - Todos os conceitos     ║");
        System.out.println("║        POO implementados!            ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
}
