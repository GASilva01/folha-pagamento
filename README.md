# Sistema de Gerenciamento de Funcionários e Folha de Pagamento

Projeto acadêmico em Java 17, organizado em camadas (model, repository, service, view e exception), com armazenamento em memória e testes JUnit 5.

## Regras de remuneração adotadas

- **CLT:** salário-base + benefício mensal; desconto tributário demonstrativo de 8% sobre o bruto.
- **PJ:** valor por projeto × quantidade de projetos; sem desconto tributário definido neste exemplo.
- **Comissionado:** salário-base + percentual sobre vendas; desconto tributário demonstrativo de 11% sobre o bruto.

Os percentuais são regras didáticas para a atividade e não representam cálculo fiscal ou folha legal. A remuneração líquida é o bruto menos tributos aplicáveis. O identificador é único, e o CPF passa pela validação dos dígitos verificadores.

## Executar

Com JDK 17 ou superior e Maven instalado:

```bash
mvn clean package
java -jar target/folha-pagamento-1.0.0.jar
```

O programa inicia com um exemplo e apresenta um menu para cadastrar, listar, consultar, remover, emitir relatório e totalizar a folha. Os dados ficam apenas na memória e são perdidos ao encerrar o programa.

## Executar testes

```bash
mvn test
```

Os testes cobrem cálculos, validações, polimorfismo, operações de repositório e serviço, e exceções. As classes de inicialização (`Main`) e interfaces não requerem teste unitário próprio; os contratos das interfaces são exercitados nos testes das classes concretas.

## Histórico de desenvolvimento

Para demonstrar o ciclo TDD solicitado, mantenha os testes e a implementação em commits progressivos no repositório GitHub, por exemplo: testes iniciais (Red), implementação mínima (Green) e refatoração. Este pacote não inclui um histórico Git pré-fabricado; os commits devem refletir o trabalho real da equipe.
