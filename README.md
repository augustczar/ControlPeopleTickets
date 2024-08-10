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

## Exemplo cadastrar pessoa:
### Ulilizar suas preferencias de configuração de `portas` do seu arquivo yaml
 - Microservice: personservice 
 
 - endpoint: "/pesssoas/cadastrar"
 
 - Postman - `http://localhost:8081/personService/pessoas/cadastrar`

massa para dados:
 
 - `{ "nome": "Escorpião Rei", "cpf": "11111111111", "dataNascimento": "2005-07-04", "cep": "12345-345", "logradouro": "Rua Deserto", "bairro": "Sol Nascente", "uf": "DF", "cidade": "Brasilia" }`

 - endpoint: "/pessoas/listar"
 
 - Postman - `http://localhost:8081/personService/pessoas/listar`

    * copiar `uuid` da pessoa cadastrada para utilizar no cadastro de boleto.
 
## Exemplo cadastrar boleto:
 - Microservice: ticketservice

 - endpoint: "/boletos/criar"

 - Postman - `http://localhost:8082/ticketService/boletos/criar`
massa para dados:
 	* "utilizar uuid da pessoa a ser vinculada ao boleto" 

 - `{"pessoaId": "b7ed973d-6aed-4d35-b7da-1fcffca374b2", "valorDocumento": 9999.99, "dataVencimento": "2024-08-12", "valorPago": null, "dataPagamento": null, "status": "PENDENTE"}`
 
# Resultado Feing:
 - endpoint: "/pessoas/{pessoaId}/boletos"

 - Postman - `http://localhost:8081/personService/pessoas/b7ed973d-6aed-4d35-b7da-1fcffca374b2/boletos`
 