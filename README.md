# Gateway

Este é responsável por administrar os endpoints dos serviços.

1. [Cliente](https://github.com/AydanAmorim/costumers-microservice)
2. [Pedido](https://github.com/fysabelah/ordering-microservice)
3. Produto
4. [Logística](https://github.com/erickmatheusribeiro/tracking-microservice)
5. [Registro e descoberta de serviços](https://github.com/fysabelah/registration-discovery-services-order-management)

## Como configurar

Crie um arquivo .env no diretório. Neste adicione a chave EUREKA_SERVER.

    EUREKA_SERVER=enderço_do_servidor

Para valor da variável, segue duas possibilidades:
1. Ambos executando em localhost, logo EUREKA_SERVER=http://localhost:7070/eureka.
2. Ambos executando em container, logo EUREKA_SERVER = http://server-discovery:7070/eureka
3. Executando o servidor em localhost e o gateway em container. Neste caso, ao subir o servidor, na página inicial em 
_Instace Info_ configure EUREKA_SERVER de acordo com o ipAddr apresentando. Exemplo: http://${ipAddr}:7070/eureka.
Também será necessário remover as configurações de network e adicionar a configuração abaixo.
 
        network_mode: host
4. Caso apenas o serviço de descoberta esteja em container, basta conectar em http://localhost:7070/eureka.


      OBS.: Ao executar este em container, ele tentará se conectar a uma rede criada na aplicação de descoberta de serviços.

## Acessando os serviços

A partir do registro no [Registro e descoberta de serviços](https://github.com/fysabelah/registration-discovery-services-order-management) todos acessos 
aos métodos será feito a partir do gateway.

Portanto, para testes, considerando que está sendo executando localmente, isso inclui containes locais, seria:

   http://localhost:7071/${nome_serviço}/${resto_da_url_definida_no_request_mapping}

   http://localhost:7071/ordering-microservice/v1/order/port
   
Obs.: O nome do serviço é o definido em *spring.application.name* no _application.properties_.