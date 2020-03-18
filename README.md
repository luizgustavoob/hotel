# Hotel

Este repositório contém os projetos desenvolvidos para simular alguns processos realizados em hotéis, tais como cadastro de hóspedes e cadastros de reservas.

## Tecnologias

* [Spring Boot](https://spring.io/projects/spring-boot)
* [String Data JPA](https://spring.io/projects/spring-data-jpa)
* [Maven](http://maven.apache.org/)
* [PostgreSQL](https://www.postgresql.org/)
* [Angular 9](https://angular.io/)
* [Ng Bootstrap](https://ng-bootstrap.github.io/#/home)
* [Bootstrap](https://getbootstrap.com/)
* [Angular Font Awesome](https://fontawesome.com/how-to-use/on-the-web/using-with/angular)
* [Moment JS](https://momentjs.com/)

## Execução

Primeiramente, deve-se baixar o repositório em algum diretório físico do computador.

## Pré requisitos

* [Node](https://nodejs.org/en/)
* [Angular CLI](https://cli.angular.io/)
* [Docker](https://www.docker.com/) ou [PostgreSQL](https://www.postgresql.org/)
* [Maven](https://maven.apache.org/)

## Execução - Criação do banco de dados
Existem duas formas de se configurar o banco de dados para testar a aplicação. A primeira delas utiliza o docker, sendo que o usuário deverá inicialmente realizar o download da ferramenta. Depois, com o docker devidamente instalado, acessar o terminal e executar o comando:
```
  docker pull postgres
```
para que a imagem do postgres seja baixada e na sequência rodar o comando:
```
  docker run -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=hotel -d postgres
```
para criar e executar o container com o banco de dados.

Caso o usuário não possua o docker, deverá ser realizado o download do banco PostgreSQL manualmente e criado um banco de dados com o nome **hotel**.

## Execução - API
Com o diretório disponível no computador, deve-se acessar a pasta *hotel-api* pelo terminal e digitar o comando
```
  mvn clean package
```
para que o arquivo **.jar** da API seja gerado. Na sequência, digitar o comando
```
  java -jar target\hotel-api-0.0.1-SNAPSHOT.jar
```
e a aplicação back-end será iniciada. A API disponibiliza 2 *endpoints* para consumo, o **/people** para manipulação dos dados de hóspedes e o **/bookings** para manipulação de reservas de hotel.

### EndPoint /people (verbos HTTP)
* GET: Retorna uma lista de hóspedes.
* POST: Insere um novo hóspede. Deve ser enviado um JSON no seguinte formato:
```
{
  "nome": "Fulano da Silva",
  "documento": "123456",
  "telefone": "11223344"
}
```
* PUT: Atualiza um hóspede. Enviar na URL o código do hóspede (**/people/1**) e no corpo um JSON no seguinte formato:

```
{
  "nome": "Fulano da Silva",
  "documento": "885544",
  "telefone": "9925-2211"
}
```
* DELETE: Remove um hóspede. Enviar na URL o código do hóspede para remover (**/people/1**).

Além deles, existem 2 outros métodos que podem ser consumidos via GET. 
O primeiro é o /filter, que consulta os hóspedes a partir de um termo fornecido no *query param* da requisição, como
```
  http://localhost:8086/people/filter?param=Fulano
```
E o segundo é o /filterByBookingStatus, que consulta os hóspedes que ainda estão no hotel, ou que já fizeram checkout. Essa condição é definida em um *query param*, como
```
  http://localhost:8086/people/filterByBookingStatus?checkOutNull=S
  
  http://localhost:8086/people/filterByBookingStatus?checkOutNull=N
```

### EndPoint /bookings (verbos HTTP)
* GET: Retorna uma lista de reservas.
* POST: Insere uma nova reserva. Deve ser enviado um JSON no seguinte formato:
```
{
  "hospede": {
	"id": 1,
	"nome": "Fulano da Silva",
    	"documento": "885544",
  	"telefone": "9925-2211"
  },
  "dataEntrada": "2020-03-12T16:00:00",
  "dataSaida": "2020-03-13T08:00:00",
  "adicionalVeiculo": false
}
```
* PUT: Atualiza uma reserva . Enviar na URL o código da reserva (**/bookings/1**) e no corpo um JSON no seguinte formato:

```
{
  "hospede": {
	"id": 1,
	"nome": "Fulano da Silva",
	"documento": "885544",
	"telefone": "9925-2211"
  },
  "dataEntrada": "2020-03-12T16:00:00",
  "dataSaida": "2020-03-13T16:45:00",
  "adicionalVeiculo": true
}
```
* DELETE: Remove uma reserva. Enviar na URL o código da reserva para remover (**/bookings/1**).

## Execução - Cliente
Através de outro terminal, deve-se acessar a pasta *hotel-front* e inicialmente digitar o comando
```
  npm install
```
para que as dependências necessárias ao projeto sejam baixadas. Após isso, digitar o comando 
```
  ng serve --open
```
e a aplicação cliente será aberta no navegador. 

Em relação ao total gasto por um hóspede em todas as suas hospedagens, o valor é apresentado como um *tooltip* sobre a coluna **Valor Gasto R$**.

