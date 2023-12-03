# API Gestão de pautas e sessões de votação

## Visão Geral
Esse projeto é uma API REST que realiza a gestão de pautas e sessões de votação e promove as seguintes funcionalidades:

- Cadastro de uma nova pauta
- Abertura de uma sessão de votação em uma pauta (a sessão de votação fica aberta por um tempo determinado na chamada de abertura ou 1 minuto por default)
- Recebe os votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta)
- Contabiliza os votos e mostra o resultado da votação na pauta

## Tecnologias e Padrões Utilizados

* Arquitetura Hexagonal
* Spring Boot
* Swagger UI
* Lombok
* MapStruct
* FeignClient
* Docker e Docker Compose
* Postman

## Executando o Projeto

### Instalar Docker
https://docs.docker.com/get-docker/

### Após instalação do Docker, seguir os passos:
1. Clonar este repositório
2. Vá para o diretório raiz do repositório: cd ./dbc-sessao-votacao
3. Rodar o comando: docker-compose up

### Verificando imagens docker rodando
```
docker ps
```
Para visualizar a documentação Swagger, acesse: http://localhost:8080/swagger-ui.html



