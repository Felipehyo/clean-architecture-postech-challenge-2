# Tech Challenge - Sistema de Gerenciamento de lanchonete

O sistema tem como intuito fornecer o controle de pedidos para atender os clientes de uma lanchonete de maneira eficiente, gerenciando seus pedidos e estoques de forma adequada.

## Integrantes do Grupo
- RM354032 - Alysson Gustavo Rodrigues Maciel
- RM355969 - Vinicius Duarte Mendes Nepomuceno
- RM354090 - Lucas Pugliese de Morais Barros
- RM353273 - Felipe Pinheiro Dantas

## Event Storming do Projeto
```url
https://miro.com/app/board/uXjVKSt4Gq8=/?share_link_id=968579577663
```

## Como rodar instâncias de aplicação e Postgres

1 - Clonar o projeto
```bash
$ git clone https://github.com/ViniNepo/hexagonal-architecture-postech-challenge.git
```
2 - Rodar comando no diretório raiz do projeto para subir container com instâncias da aplicação e do banco Postgre
```bash
$ docker-compose up --build
```

## Para acessar o swagger e realizar os testes
Rota para acessar Swagger
```url
http://localhost:8080/lanchonete/v1/swagger-ui
```
Rota para acessar Swagger.yml
```url
http://localhost:8080/lanchonete/v1/api-docs
```
Dentro do Projeto no diretório "postman" há um arquivo com uma collection postman com todas as rotas mapeadas para teste
```
./postman/Pos_Tech-Arquitetura_Hexagonal-Lanchonete
```

## Como realizar um pedido

Para criar um pedido nós podemos iniciar de duas formas, sendo elas com ou sem identificação do cliente.

### Sem Identificação

Caso o cliente não queira ele pode pular a fase de cadastro e continuar para o pedido sem precisar se registrar

### Com Identificação

Quando o cliente acessar o frontend (que no caso ainda existe) ele terá a opção de se cadastrar ou de realizar o login. Para
validar se o cliente já tem um conta criada no sistema basta utilizar o endpoint de consulta passando o CPF como parâmetro:

```url
GET http://localhost:8080/lanchonete/v1/clientes?cpf={CPF}
```

Caso o registro não seja encontrado ele terá a change de se cadastrar usando o endpoint abaixo:

```url
POST http://localhost:8080/lanchonete/v1/clientes
{
    "nome": "{NOME}",
    "email": "{EMAIL}",
    "cpf": "{CPF}"
}
```

Feito isso basta seguir com a criação do pedido

## Montagem do pedido

Após o cliente avançar na tela de identificação o sistema irá ou não refenrenciar o cliente ao pedido e irá criar um pedido. Para
fazer isso precisamos chamar o endopoint de criação do pedido:

```url
POST localhost:8080/lanchonete/v1/pedidos
{
    "clienteId": {CLIENT_ID}, //(pode ser NULL também)
    "pedidosProdutos": [
        {
            "produtoId": {PRODUTO_ID},
            "quantidade": {QUANTIDADE}
        }
    ]
}
```

Obs: Pensando que o frontend terá registrado todas as opções que o cliente escolheu na tela e enviado para o backend nós 
teremos uma única chamada no sistema criando o pedido.

Após isso podemos realizar o checkout pelo endpoint: 

```url
PATCH localhost:8080/lanchonete/v1/pedidos/{PEDIDO_ID}/fake-checkout
```

Além disso outras funções como consultar um pedido ou todos, atualizar o estado de um pedido ou deletar. Essas chamadas 
estão disponíveis no collection do Postman.


## Cadastro de produdos

Para termos produtos disponiveis para fazer os pedido é preciso adicionar usando o endpoint:

```url
PATCH localhost:8080/lanchonete/v1/produtos
{
    "nome": "{NOME_PRODUTO}",
    "descricao": "{DESCRIÇÃO_PRODUTO}",
    "categoria": "{CATEGORIA}",
    "preco": {PREÇO}
}
```

Para mais funções como listar por ID, listar todos, por categoria, atualizar e deletar estão dispononiveis pela collection.
## Dockerhub com a imagem da aplicação
```url
https://hub.docker.com/repository/docker/lpugliese/fiap-clean-restaurant-application/general
```

## CMDs para push da imagem para o Dockerhub
```bash
docker build -t lpugliese/fiap-clean-restaurant-application .

docker push lpugliese/fiap-clean-restaurant-application:latest
```

## Arquitetura do k8s
![architecture-k8s.png](architecture-k8s.png)