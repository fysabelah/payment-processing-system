# Sistema de Gerenciamento de Clientes (Customers) - Desafio Tech Challenge - Fase 4

Este repositório refere-se ao microsserviço de pedidos. No total, o projeto envolve 4 microsserviços, sendo eles:

1. **Cliente** *(este)*
2. Produto
3. Pedido
4. Logística

# Principais recursos deste microserviço

Responsável por cadastrar informações dos clientes, assim será possível:
* Incluir novos clientes;
* Alterar informações;
* Desativar um cliente (Manterá o histórico somente mas não será possível realizar ações);
* Consultar todos os clientes;
* Consultar clientes por status (ativo/inativo);

## Tecnologias

* Spring Boot para a estrutura do serviço
* Spring Data JPA para manipulação de dados dos clientes
* Spring Cloud Stream para comunicação baseada em eventos com outros microsserviços
* PostgreSQL para persistência 