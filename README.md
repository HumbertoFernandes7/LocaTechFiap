# LocaTechFiap

Este projeto é uma API RESTful para um sistema de locação de carros, desenvolvida como parte de um curso da FIAP.

## Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias:

* **Spring Boot**: Versão 3.5.0
* **Java**: Versão 21
* **Spring Boot Starter JDBC**: Para integração com banco de dados via JDBC
* **Spring Boot Starter Web**: Para construção de aplicações web e RESTful
* **Spring Boot Starter Validation**: Para validação de dados
* **H2 Database**: Banco de dados em memória para desenvolvimento e testes
* **Lombok**: Para reduzir o boilerplate code

## Configuração e Execução

Para configurar e executar o projeto, siga os passos abaixo:

1.  **Pré-requisitos**:
    * Java Development Kit (JDK) 21 ou superior.
    * Apache Maven.

2.  **Clonar o repositório**:
    ```bash
    git clone <URL_DO_REPOSITORIO>
    cd LocaTechFiap
    ```

3.  **Compilar e Executar**:
    Você pode executar a aplicação utilizando o Maven:
    ```bash
    mvn spring-boot:run
    ```

A aplicação será iniciada na porta padrão do Spring Boot (8080).

## Banco de Dados

O projeto utiliza um banco de dados H2 em memória. As configurações estão no arquivo `application.properties`.

* **URL do Console H2**: `http://localhost:8080/h2-console`
* **JDBC URL**: `jdbc:h2:mem:locatech`
* **Usuário**: `sa`
* **Senha**: `password`

Os scripts SQL para criação de tabelas e inserção de dados iniciais estão em `src/main/resources/data.sql`.

## Endpoints da API

A API oferece endpoints para gerenciar Pessoas, Veículos e Aluguéis.

### 1. Pessoas

Gerenciamento de pessoas. A entidade `Pessoa` possui os campos: `id`, `nome`, `cpf`, `telefone` e `email`.

* **Listar todas as pessoas**: `GET /pessoas`
    * Parâmetros de query: `page` (int), `size` (int)
    * Exemplo: `GET /pessoas?page=1&size=10`
* **Buscar pessoa por ID**: `GET /pessoas/{id}`
* **Criar nova pessoa**: `POST /pessoas`
    * Corpo da requisição (JSON):
        ```json
        {
          "nome": "Humberto",
          "cpf": "123.456.789-00",
          "telefone": "(11)97778-2014",
          "email": "humberto@example.com"
        }
        ```
* **Atualizar pessoa existente**: `PUT /pessoas/{id}`
    * Corpo da requisição (JSON): (mesmo formato do POST)
* **Excluir pessoa**: `DELETE /pessoas/{id}`

### 2. Veículos

Gerenciamento de veículos. A entidade `Veiculo` possui os campos: `id`, `marca`, `modelo`, `placa`, `ano`, `cor` e `valorDiaria`.

* **Listar todos os veículos**: `GET /veiculos`
    * Parâmetros de query: `page` (int), `size` (int)
    * Exemplo: `GET /veiculos?page=1&size=10`
* **Buscar veículo por ID**: `GET /veiculos/{id}`
* **Criar novo veículo**: `POST /veiculos`
    * Corpo da requisição (JSON):
        ```json
        {
          "marca": "BMW",
          "modelo": "X5",
          "placa": "ABC-1234",
          "ano": 2019,
          "cor": "Preto",
          "valorDiaria": 100.00
        }
        ```
* **Atualizar veículo existente**: `PUT /veiculos/{id}`
    * Corpo da requisição (JSON): (mesmo formato do POST)
* **Excluir veículo**: `DELETE /veiculos/{id}`

### 3. Aluguéis

Gerenciamento de aluguéis. A entidade `Aluguel` possui os campos: `id`, `pessoaId`, `veiculoId`, `veiculoModelo`, `pessoaCpf`, `pessoaNome`, `dataInicio`, `dataFim` e `valorTotal`.

* **Listar todos os aluguéis**: `GET /alugueis`
    * Parâmetros de query: `page` (int), `size` (int)
    * Exemplo: `GET /alugueis?page=1&size=10`
* **Buscar aluguel por ID**: `GET /alugueis/{id}`
* **Criar novo aluguel**: `POST /alugueis`
    * Utiliza `AluguelRequestDTO` para a entrada, que contém `pessoaId`, `veiculoId`, `dataInicio` e `dataFim`. O `valorTotal` é calculado automaticamente com base no `valorDiaria` do veículo e na duração do aluguel.
    * Corpo da requisição (JSON):
        ```json
        {
          "pessoaId": 1,
          "veiculoId": 1,
          "dataInicio": "2024-10-01",
          "dataFim": "2024-10-15"
        }
        ```
* **Atualizar aluguel existente**: `PUT /alugueis/{id}`
    * Corpo da requisição (JSON): (formato da entidade `Aluguel`)
* **Excluir aluguel**: `DELETE /alugueis/{id}`

## Tratamento de Erros

A API possui um tratamento de erros global para as seguintes situações:

* **Recurso não encontrado (404 Not Found)**: Lançado quando uma entidade não é encontrada pelo ID. Retorna um `ResourceNotFoundDTO` com a mensagem de erro e o status.
* **Argumentos de método inválidos (400 Bad Request)**: Lançado quando a validação `@Valid` falha em um DTO de requisição. Retorna um `ValidationErrorDTO` com uma lista de erros de campo e o status.
