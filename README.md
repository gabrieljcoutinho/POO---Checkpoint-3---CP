# Projeto FiapRide - [Seu Nome Completo]

## 📋 Informações do Aluno

- **Nome:** Gabriel Jorge Coutinho
- **RM:** 565441
- **Turma:** 2CCPW
- **Curso:** Análise e Desenvolvimento de Sistemas / Engenharia de Software

---

## 🎯 Descrição do Projeto

Este projeto é o resultado do aprendizado nas aulas 1-9 de Programação Orientada a Objetos, onde desenvolvemos o sistema **FiapRide** (aplicativo de mobilidade urbana).

O FiapRide simula um sistema de corridas estilo Uber/99, com passageiros, motoristas, veículos (carros e motos) e viagens, aplicando progressivamente todos os pilares da POO: encapsulamento, herança, polimorfismo e abstração.

---

## ✅ Checklist de Implementação

- [x] Aula 1 - Classes e Objetos
- [x] Aula 2 - Métodos
- [x] Aula 3 - Encapsulamento
- [x] Aula 4 - Construtores
- [x] Aula 5 - Associação
- [x] Aula 6 - Herança
- [x] Aula 7 - Polimorfismo
- [x] Aula 8 - Classes Abstratas
- [x] Aula 9 - Interfaces

---

## 🤔 Perguntas de Reflexão

### Aula 1 - Classes e Objetos

**Pergunta:** Por que precisamos criar uma classe `Passageiro`? Não seria mais fácil apenas criar variáveis soltas no `main`, como `String nomeAna = "Ana"` e `double saldoAna = 50.0`?

**Pense:** E se o FiapRide tiver 1 milhão de usuários? Como a Classe ajuda a resolver isso?

**Sua Resposta:**

Criar variáveis soltas no `main` funciona para 1 passageiro, mas rapidamente se torna inviável. Para 1 milhão de usuários, teríamos `nomeAna`, `nomeBruno`, `nomeCarlos`... o código seria impossível de manter.

A classe `Passageiro` resolve isso de três formas. Primeiro, ela agrupa nome, CPF, saldo e email em um único "molde" — ao invés de 4 variáveis por pessoa, criamos 1 objeto que carrega tudo. Segundo, ela encapsula o comportamento junto com os dados: o método `adicionarSaldo()` pertence ao passageiro, não fica solto pelo código. Terceiro, com 1 milhão de usuários bastaria uma lista (`ArrayList<Passageiro>`) e um loop — o que seria impossível com variáveis individuais.

A classe transforma um conjunto caótico de variáveis desconexas em uma entidade coesa com identidade e responsabilidade próprias. Isso é exatamente o que o mundo real pede: um passageiro não é só um nome — ele tem saldo, histórico, e realiza ações no sistema.

---

### Aula 2 - Métodos

**Pergunta:** Se não podemos simplesmente fazer `passageiro.saldo += 100` diretamente no código principal, por que dá tanto trabalho criar um método específico chamado `adicionarSaldo(valor)` para fazer isso? Quais seriam os riscos para a nossa startup de mobilidade se deixássemos qualquer programador a alterar o saldo diretamente?

**Sua Resposta:**

O acesso direto `passageiro.saldo += 100` funciona tecnicamente, mas é uma bomba-relógio para uma startup. Os riscos são concretos:

**Sem validação:** qualquer valor passa, inclusive `passageiro.saldo += -999999`. Com o método `adicionarSaldo(valor)`, a primeira linha verifica `if (valor <= 0)` e rejeita. Direto no campo: sem proteção.

**Sem rastreabilidade:** quando o método é chamado, podemos adicionar um log, uma notificação, um evento para auditoria financeira. Com `+=` espalhado pelo código, nunca sabemos quem alterou o saldo, quando, e por quê.

**Sem regras de negócio:** o FiapRide pode ter uma regra de "saldo máximo de R$2.000" ou "precisa confirmar identidade para depósitos acima de R$500". Essas regras ficariam espalhadas em 50 lugares diferentes ao invés de em 1 método.

**Manutenção impossível:** se a regra mudar, temos que procurar `saldo +=` em todo o projeto. Com o método, mudamos em um lugar e o sistema inteiro se adapta.

O método é o único ponto de entrada confiável para uma operação crítica. Em sistemas financeiros, isso não é opcional — é obrigação.

---

### Aula 3 - Encapsulamento

**Pergunta:** No nosso código, os atributos são `private`, mas os métodos `getSaldo()` e `getNome()` são `public`. Por que é seguro deixar o `get` público, mas perigoso deixar o atributo original público?

Pense bem: Qual a diferença entre dar a alguém uma CÓPIA de um documento seu, e entregar o documento ORIGINAL para a pessoa rasgar?

**Sua Resposta:**

A analogia do documento é perfeita para entender isso.

Quando você chama `passageiro.getSaldo()`, recebe uma **cópia** do valor — um `double` por valor. Você lê `150.0`. Se tentar `passageiro.getSaldo() = 9999`, o compilador simplesmente não permite: você não tem o original, só a cópia.

Quando o atributo é `public`, `passageiro.saldo = 9999` funciona diretamente. Você recebeu o original e pode rasgá-lo, reescrever, colocar valor negativo, qualquer coisa.

O `get` público é seguro porque devolve uma representação do dado, não o dado em si. O atributo `private` garante que a única forma de alterar o saldo é pela porta controlada: o método `setSaldo()` ou `adicionarSaldo()`, que têm validações.

Há ainda outro benefício: podemos mudar a implementação interna sem quebrar nada. Se amanhã o saldo passar a ser armazenado em centavos (inteiro) por precisão, o `getSaldo()` continua devolvendo um `double` dividindo por 100. Quem usa o método não percebe a mudança interna. Com `public saldo`, mudamos o tipo e quebramos 200 lugares no código.

---

### Aula 4 - Construtores

**Pergunta:** Na nossa classe `Veiculo`, nós tomamos duas decisões arquitetônicas muito importantes:
1. Nós **não** criamos o método `setModelo()`.
2. O `setPlaca()` foi criado como `private`, e criamos um método público chamado `atualizarPlaca()` para acessá-lo.

Pensando no mundo real e no Clean Code: Por que é um erro gravíssimo clicar em "Gerar Getters e Setters para tudo" automaticamente na sua IDE? Como as nossas duas decisões acima protegem o sistema de fraudes e falhas de lógica?

**Sua Resposta:**

"Gerar Getters e Setters para tudo" é o atalho mais perigoso de um desenvolvedor júnior, porque automaticamente quebra o encapsulamento de todos os atributos — que se tornam efetivamente públicos, só com uma camada de método sem sentido.

No caso do FiapRide:

**`setModelo()` não existe:** O modelo de um carro não muda. Um Toyota Corolla não vira um Honda Civic. Se gerássemos `setModelo()` automaticamente, qualquer código poderia alterar o modelo de um veículo em produção, corrompendo dados históricos de viagens, relatórios e registros da Detran. Sem o setter, essa alteração é fisicamente impossível.

**`setPlaca()` é `private`:** A placa é um identificador legal do veículo. Alterá-la não é uma operação trivial — envolve processo burocrático real. O método `atualizarPlaca()` público existe para representar esse processo: ele valida, loga, e executa a mudança de forma controlada. Se `setPlaca()` fosse público, um bug ou ataque poderia trocar a placa de um veículo roubado para um legal sem nenhuma barreira.

O princípio é: **cada atributo deve ter exatamente a acessibilidade que o negócio exige, não mais**. Código que muda o modelo de um carro ou a placa diretamente deveria causar erro de compilação — e com nossa arquitetura, causa.

---

### Aula 5 - Associação

**Pergunta:** No construtor da `Viagem`, nós exigimos o objeto inteiro `Passageiro solicitante`. Se o nosso resumo só precisa imprimir a String do nome da pessoa, não seria mais fácil e mais leve pedir apenas a String do nome no construtor da Viagem (`String nomeDoPassageiro`) em vez de objeto todo?

Pense nas regras de negócio: O que acontece na hora que a Viagem acaba e o sistema precisa descontar o saldo? Se a Viagem tiver apenas a String "Ana Silva", ela consegue mexer no dinheiro dela?

**Sua Resposta:**

Essa é a diferença entre **dado** e **entidade**. Uma `String "Ana Silva"` é apenas um dado — não tem comportamento, não tem saldo, não pode fazer nada.

O objeto `Passageiro` é uma entidade: ele sabe debitar seu próprio saldo (`debitarSaldo()`), tem email para notificação, tem CPF para identificação legal, e no futuro terá histórico de viagens.

Quando a viagem termina (método `concluirViagem()`), a linha `passageiroSolicitante.debitarSaldo(valorTarifa)` só é possível porque temos o objeto. Com uma String, seria necessário buscar o passageiro em algum banco de dados novamente, introduzindo complexidade e possíveis falhas de sincronização.

Há ainda uma questão de integridade: se o passageiro foi cadastrado como "Ana Silva" mas mudou o nome para "Ana Costa" durante a viagem, o objeto reflete a entidade real, enquanto a String seria um dado desatualizado congelado no momento da criação da viagem.

Receber o objeto inteiro no construtor segue o princípio "Tell, Don't Ask" — ao invés de pegar dado e tomar decisão por fora, dizemos ao objeto para fazer a operação ele mesmo.

---

### Aula 6 - Herança

**Pergunta:** No nosso código, a mãe `Veiculo` possui os atributos `placa` e `modelo` como `private`. Quando o `Carro` herda de `Veiculo`, ele recebe esses atributos, mas o código dentro de `Carro` NÃO consegue fazer `this.placa = "ABC"`. Ele é obrigado a usar o `super()` ou o `setPlaca()`.

Por que o Java não deixa a filha alterar as variáveis privadas da mãe diretamente? Qual o princípio das aulas passadas que isso está protegendo?

**Sua Resposta:**

O Java está protegendo o **Encapsulamento** (Aula 3) — e a herança não deve ser uma exceção a esse princípio.

Se `Carro` pudesse fazer `this.placa = "XYZ"` diretamente, toda a proteção que colocamos no `setPlaca()` e `atualizarPlaca()` seria facilmente contornada. Bastaria criar uma subclasse e acessar o atributo diretamente, tornando o encapsulamento inútil.

O `private` significa "só a minha classe mexe em mim". Nem filhas, nem clientes externos. Se a intenção for que filhas possam acessar o atributo diretamente, usa-se `protected`. Mas no caso de `placa`, a decisão deliberada foi manter `private` e forçar o uso do `atualizarPlaca()` — que valida, loga e representa o processo real.

Isso também previne erros difíceis de encontrar: se `Carro` e `Moto` pudessem alterar `placa` livremente, poderíamos ter inconsistências onde o estado interno do `Veiculo` é válido segundo a mãe mas inválido segundo as filhas. A barreira do `private` garante que o estado do objeto é sempre controlado por quem o definiu.

---

### Aula 7 - Polimorfismo

**Pergunta:** No nosso loop `for (Veiculo veiculo : frota)`, a variável `veiculo` é do tipo genérico `Veiculo`. Se esquecêssemos de criar o método `calcularAutonomia()` lá na classe mãe `Veiculo`, nós conseguiríamos chamá-lo dentro do loop, mesmo sabendo que ele existe dentro do `Carro` e da `Moto`? Por que o contrato precisa existir na base da hierarquia?

**Sua Resposta:**

Não conseguiríamos. E entender por que é fundamental para entender o polimorfismo.

Quando declaramos `Veiculo veiculo`, o compilador Java só enxerga o que a classe `Veiculo` oferece. Ele não sabe, em tempo de compilação, se `veiculo` será um `Carro` ou uma `Moto` durante a execução. Por isso, ele consulta o "contrato" da classe `Veiculo` para verificar se `calcularAutonomia()` é um método válido.

Se o método não existe em `Veiculo`, o compilador rejeita `veiculo.calcularAutonomia()` com erro: "Cannot find symbol". Mesmo que **ambas** as subclasses implementem o método, o compilador não aceita — porque a referência é do tipo `Veiculo`, não `Carro` ou `Moto`.

Quando declaramos `calcularAutonomia()` como `abstract` em `Veiculo`, fazemos duas coisas ao mesmo tempo: (1) criamos o contrato que o compilador aceita no loop, e (2) obrigamos **todas** as subclasses a implementar. É a garantia de que qualquer `Veiculo` do sistema, presente e futuro, sempre saberá calcular sua autonomia.

Em resumo: o contrato na base da hierarquia é o que permite o polimorfismo funcionar. Sem ele, teríamos que fazer casts para `Carro` e `Moto` individualmente, destruindo a elegância do loop genérico.

---

### Aula 8 - Classes Abstratas

**Pergunta:** Faz sentido existir um objeto que é APENAS `Veiculo`, sem ser carro, moto, caminhão ou nada disso? Você já entrou numa concessionária e comprou "um veículo genérico", sem ser carro, moto, caminhão ou nada disso?

Por que, então, no código, precisamos EXPLICITAMENTE dizer ao Java que `Veiculo` é `abstract`? Por que ele não deduz isso sozinho?

Pense: Se esquecermos de colocar `abstract`, qual o risco que corremos? Alguém pode criar `new Veiculo()` e quebrar a lógica do nosso sistema?

**Sua Resposta:**

No mundo real, "veículo genérico" não existe — é sempre uma categoria concreta. No código, porém, o Java não tem como inferir essa intenção sem a palavra `abstract` explícita.

O Java é uma linguagem de tipagem estática: ele segue regras sintáticas rígidas, não interpreta semântica de negócio. Mesmo que `Veiculo` tivesse um método `abstract calcularAutonomia()` (o que já impediria a instância), sem o `abstract` na classe o Java permitiria a criação de um objeto `Veiculo` com implementação vazia ou padrão, o que seria semanticamente errado.

O risco de esquecer o `abstract` é real: `new Veiculo("ABC", "Generico", "Azul")` compilaria e rodaria. Esse objeto não tem tipo de combustível, não tem portas, não tem cilindradas — é um fantasma no sistema. Poderia entrar em uma `ArrayList<Veiculo>`, participar de cálculos de autonomia (retornando 0.0 se não for obrigatório), e gerar dados corrompidos em relatórios.

A palavra `abstract` é uma declaração explícita de intenção de design: "esta classe existe para ser especializada, nunca instanciada". É documentação executável — o compilador a força. Sem ela, dependemos de que nenhum programador (inclusive nós mesmos no futuro) jamais esqueça essa regra não documentada.

---

### Aula 9 - Interfaces

**Pergunta:** Por que Java permite herança simples (apenas uma mãe), mas múltipla implementação de interfaces (vários contratos)?

Pense: Se `CarroEletrico` pudesse herdar de `Veiculo` E de `Bateria` ao mesmo tempo (herança múltipla), o que aconteceria se AMBAS as mães tivessem um método chamado `ligar()`?

Como as interfaces resolvem esse problema?

**Sua Resposta:**

O "Problema do Diamante" é o motivo central. Imagine:

```
Veiculo.ligar()  →  liga o motor
Bateria.ligar()  →  liga o carregamento

CarroEletrico extends Veiculo, Bateria
// Qual ligar() herda? Conflito irresolvível.
```

O Java simplesmente proibiu herança múltipla para evitar essa ambiguidade. Não existe forma elegante de resolver automaticamente: qualquer escolha automática seria arbitrária e quebraria a semântica.

Interfaces resolvem isso porque **não têm implementação de estado** (antes do Java 8, nem `default methods`). Uma interface é um contrato puro: diz "você precisa implementar `ligar()`", mas não diz como. Quando `CarroEletrico implements Conduzivel, Recarregavel`, e ambas exigem `ligar()`, o compilador simplesmente exige que `CarroEletrico` forneça **uma** implementação. Não há conflito porque não há código herdado para colidir.

O `CarroEletrico` decide o que `ligar()` significa para ele: talvez acione motor E bateria na mesma chamada. A classe tem o controle total da implementação, sem herdar lógica conflitante de nenhuma das interfaces.

Essa é a distinção fundamental: herança traz **comportamento e estado** (risco de conflito), interfaces trazem apenas **contratos** (composição segura de responsabilidades).

---

## 🚀 Desafios Técnicos Implementados

### Desafio Pessoal (Seu Projeto)

**Qual foi o domínio que você escolheu para seu projeto pessoal?**
Sistema de Mobilidade Urbana (FiapRide) — aplicativo de corridas com passageiros, motoristas, veículos e viagens.

**Quais classes você criou?**
- `Passageiro` — usuário que solicita corridas
- `Veiculo` — classe abstrata base da hierarquia
- `Carro` — filha de `Veiculo`, implementa `Conduzivel`
- `Moto` — filha de `Veiculo`, implementa `Conduzivel`
- `Viagem` — associa `Passageiro` + `Veiculo`, gerencia o ciclo de vida da corrida
- `Motorista` — parceiro motorista com veículo associado
- `Conduzivel` — interface com contrato de operação de veículo

**Qual foi o maior desafio técnico que você enfrentou?**

O maior desafio foi projetar a classe `Veiculo` de forma que o encapsulamento da Aula 3 funcionasse corretamente dentro da herança da Aula 6.

O problema concreto: `placa` e `modelo` são `private` em `Veiculo`. Quando `Carro` e `Moto` estendem `Veiculo`, elas precisam dos valores para construir o objeto, mas não podem acessar os campos diretamente. A solução foi usar `super(placa, modelo, cor)` no construtor de cada filha, delegando a inicialização à mãe sem violar o encapsulamento.

O segundo ponto difícil foi entender que a interface `Conduzivel` precisava ser implementada tanto por `Carro` quanto por `Moto`, enquanto ambas já estendiam `Veiculo`. Testar o loop polimórfico com `Conduzivel[] veiculosConduziveis = {carro1, moto1}` e perceber que o compilador aceitava porque ambas implementam a interface foi o momento de "clic" da Aula 9.

A solução para acessar `getModelo()` dentro do loop de `Conduzivel` foi usar `instanceof` e cast explícito — o que ensina que interfaces são um contrato mais estreito que a classe completa, e às vezes é necessário verificar o tipo real do objeto em runtime.

---

## 🏁 Conclusão

**O que você aprendeu nestas 9 aulas?**

Aprendi que POO não é sobre sintaxe Java — é sobre modelar o mundo real de forma que o código resista à mudança e ao crescimento. Cada aula adicionou uma ferramenta ao arsenal:

Encapsulamento protege o estado; herança reutiliza e especializa; polimorfismo deixa o código tratar o geral sem perder o específico; interfaces permitem compor comportamentos sem herança múltipla perigosa. Juntos, esses princípios permitem que um sistema de mobilidade urbana com 1 usuário seja exatamente o mesmo código de um com 1 milhão — só a instâncias mudam, não a estrutura.

**Qual conceito foi mais difícil de entender?**

Polimorfismo foi o mais difícil, especialmente a distinção entre polimorfismo em tempo de compilação e em tempo de execução. Entender que `Veiculo veiculo = new Carro(...)` é válido (porque `Carro` IS-A `Veiculo`), mas que `veiculo.getKmPorLitro()` não compila (porque `Veiculo` não tem esse método) levou tempo para internalizar.

A superação veio ao perceber que o compilador trabalha com o **tipo da referência** (esquerda do `=`), enquanto a JVM em execução usa o **tipo real do objeto** (direita do `=`). São dois momentos diferentes com regras diferentes.

**O que você melhoraria no seu projeto se pudesse refazer?**

Adicionaria um `ServicoDeViagem` ou `FiapRideService` para centralizar a lógica de negócio (buscar veículo disponível, casar passageiro com motorista, calcular surge pricing) ao invés de deixar tudo no `main`. Isso separaria melhor as responsabilidades — o `main` seria apenas orquestrador, e as regras de negócio ficariam em classes especializadas, facilitando testes unitários no futuro.

---

*"A melhor forma de aprender programação é PROGRAMANDO."*

**Bons estudos!** 🚀
