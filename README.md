# Sistema de Processamento de Pagamentos

Sistema de processamento de pagamentos de cartão de crédito.

## Hackathon FIAP

Este repositório agrupa o desafio final da Pós-Graduação em Arquitetura e Desenvolvimento Java.

### Desenvolvedores

- [Aydan Amorim](https://github.com/AydanAmorim)
- [Danilo Faccio](https://github.com/DFaccio)
- [Erick Ribeiro](https://github.com/erickmatheusribeiro)
- [Isabela França](https://github.com/fysabelah)

## Fluxo de uso

1. Serviço de _cliente_: realiza as operações de cadastro e outras mais.
    * Para os outros serviços será necessário um cliente cadastrado. Por padrão, está sendo cadastrado dois clientes,
      Sudo Su Dev e Sudo Su QA.
    * Sudo Su Dev: Cliente cadastrados para fins de desenvolvimento.
    * Sudo Su QA: Cliente cadastrado para ser utilizado pela FIAP.
2. Serviço de _autenticação_: responsável por gerenciamento de usuários e criação de tokens.
    * Por fins de requisitos, todas as rotas, com exceção das rotas de documentação e criação de tokens, são
      autenticadas.
    * É necessário que o _serviço de clientes_ esteja executando em conjunto com o de autenticação, caso queira executar
      local, visto que o serviço faz requisição ao outro.
    * São criados dois usuários inicias que podem ser utilizados caso não queria realizar cadastros, sudosu e adj2,
      referente respectivamente a, Sudo Su Dev e Sudo Su QA. Por questões de praticidades as informações serão
      adicionadas aqui.

      ```
      {
         "usuario": "adj2",
         "senha": "adj@1234"
      }
      ```

      ```
      {
         "usuario": "sudosu",
         "senha": "1amr00t"
      }
      ```
    * Com as informações de usuário acima, já seria possível realizar a criação dos tokens.

## Como executar

Clone o projeto com o comando abaixo.

```
git clone --recursive https://github.com/fysabelah/payment-processing-system.git
```

Crie um arquivo .env no diretório root do projeto. Um exemplo de arquivo pode ser visto abaixo. Os valores podem ser
alterados, porém as chaves devem ser mantidas.

```
PROFILE=prod

# PostgreSQL
POSTGRES_DATABASE_USERNAME=postgres
POSTGRES_DATABASE_PASSWORD=i@mr00t

#PgAdmin
PGADMIN_DEFAULT_EMAIL=payment@api.com.br
PGADMIN_DEFAULT_PASSWORD=r00t

# Gateway
# Quando container http://server-discovery:7070/eureka
# Quando localhost http://localhost:7070/eureka
EUREKA_SERVER=http://server-discovery:7070/eureka

CUSTOMER_ADDRESS=lb://customer-service
AUTHENTICATION_ADDRESS=lb://authentication-service
CARD_ADDRESS=lb://card-service
PAYMENT_ADDRESS=lb://payment-service

JWT_SERVER=authentication-service:7072
```

Por fim, execute o comando abaixo para criar os containers. Sugiro criar o container e caso deseje utilizar um container
local, seria apenas parar o container. 

**Obs.:** Caso queria executar algum serviço local, no README.md de cada serviço, há explicação sobre suas chaves e como preenchê-las para tal.

```
docker compose up
```

Ao subir os container, os serviços não expõe suas portas, desta forma, todas operações serão obrigatoriamente roteadas
via container.

### Sobre as chaves

* PROFILE: _dev_ e _prod_. Dependendo do escolhido, pode ocorrer de mudar operações.
* POSTGRES_: Informações referentes a conexão com PostgreSQL.
* PGADMIN_: Informações referentes com a conexão do PgAdmin.
* EUREKA_SERVER: Conexão do gateway com a descoberta do serviço. Possibilidades:
    * Ambos em container: http://server-discovery:7070/eureka
    * Descoberta de serviço em container e gateway local: http://localhost:7070/eureka
* CUSTOMER_ADDRESS, AUTHENTICATION_ADDRESS, CARD_ADDRESS, PAYMENT_ADDRESS: informações referente a conexão com os
  serviços. Possibilidades:
    * Quando gateway em container e o serviço local: http://host.docker.internal:${porta_app}.
      Exemplo:http://host.docker.internal:7072
    * Quando tudo em container: lb://${spring.application.name}. lb é devido ao Eureka, referenciando _load balance_.
      Exemplo: lb://customer-service
* JWT_SERVER: referente a aplicação que possível a chave pública responsável pela geração de tokens. Possibilidades:
    * Ambos localmente: localhost:7072
    * Ambos em docker: authentication-service:7072.
    * Gateway em docker e aplicação local: host.docker.internal:7072

## Acesso a documentação

Todos os serviços possuem rotas de documentação. Estas seguem abaixo.

### _Autenticação_

* Json: http://localhost:7071/api/autenticacao/documentation
* Swagger-UI: http://localhost:7071/api/autenticacao/doc/user.html

### _Cartão_

* Json: http://localhost:7071/api/cartao/documentation
* Swagger-UI: http://localhost:7071/api/cartao/doc/card.html

### _Cliente_

* Json: http://localhost:7071/api/cliente/documentation
* Swagger-UI: http://localhost:7071/api/cliente/doc/customer.html

### _Cliente_

* Json: http://localhost:7071/api/pagamentos/documentation
* Swagger-UI: http://localhost:7071/api/pagamentos/doc/payment.html
