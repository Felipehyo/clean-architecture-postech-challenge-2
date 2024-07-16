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
