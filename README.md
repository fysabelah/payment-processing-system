# Sistema de Gerenciamento de Clientes (Customers) - Desafio Tech Challenge - Fase 4

Este repositório refere-se ao microsserviço de pedidos. No total, o projeto envolve 4 microsserviços, sendo eles:

1. **Cliente** *(este)*
2. Produto
3. Pedido
4. Logística

5. Este microsserviço é responsável por cadastrar informações dos clientes, poderemos incluir novos clientes, alterar e desativar.

## Tecnologias

* Spring Boot para a estrutura do serviço
* Spring Data JPA para manipulação de dados dos clientes
* Spring Cloud Stream para comunicação baseada em eventos com outros microsserviços
* PostgreSQL para persistência 