# ControlPeopleTickets

## Git Clone:
- git@github.com:augustczar/ControlPeopleTickets.git

## Microservices:
- PersonService: port 8081 (http) with context path '/personService'
- Ticktservice: port 8082 (http) with context path '/ticketService'

## Pré-requisitos
- Java 17
- Maven
- PostgreSQL
- springframework:: boot 3.3.2, jpa, validation, web, openfeign, mockmvc e cloud
- springdoc: openapi
- jasperreports
- lowagie: itext
- lombok

## Configuração do Banco de Dados
1. Crie dois banco de dados PostgreSQL com os nomes `controlpeople` e `controlticket`.
2. Atualize as credenciais do banco de dados no arquivo `application.yml` de cada microservice.

## Executando a Aplicação
1. Navegue até o diretório do projeto.
2. Execute o comando `mvn spring-boot:run` para iniciar os microserviços.

## Testes
- Para executar os testes unitários, utilize o comando `mvn test`.

## Documentação da API
- Acesse a documentação Swagger em `http://localhost:8081/personService/swagger-ui/index.html` para o serviço de pessoas.
- Acesse a documentação Swagger em `http://localhost:8082/ticketService/swagger-ui/index.html` para o serviço de boletos.

## Geração de Relatórios
- Acesse `http://localhost:8081/personService/relatorios/pessoas` para baixar o relatório de pessoas em PDF.
