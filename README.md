## Objetivo do Projeto Loja Virtual
***
Disponibilizar um WEB Service com API's REST capaz de simular uma loja virtual com cadastro de pedidos, usuários e produtos. 

## Tecnologias Utilizadas
***

* Spring Boot
* Java JWT: JSON Web Token for Java and Android
* Gson
* H2 Java SQL Database
* Docker 
* Swagger
* JUnit + SpringTest

## *Deploy*
A aplicação está disponível para ser testada no Heroku através do link. 

[Link Microserviço simulador de loja](https://microservicesimulator.herokuapp.com/)

## *Segurança*

As APIs disponíveis para consumo necessitam de um token para serem executadas. Este token é gerado através da autenticação do usuário cadastrado que pode possuir dois tipos de perfis, "ROLE_USER" e/ou "ROLE_ADMINISTRATOR". Para entender melhor os níveis de segurança da aplicação observe a seguir:

### Níveis de segurança 

* ROLE_USER 
   * O cliente poderá realizar todos os consumos exceto as API's de administração de Usuário;
* ROLE_ADMINISTRATOR O usuário poderá executar qualquer uma das API's;

### **Como executar seguindo os critérios de segurança ?**

Para executar uma API é necessário capturar o token de acesso, para fazer isso siga os passos a seguir. 

1. Execute a API https://microservicesimulator.herokuapp.com/auth utilizando o usuário cadastrado default administrador\admin123, observe baixo o json utilizado no body da requisição. 

```
{
   "user": "administrador",
   "password": "teste123"

}
```

2. Após a execução a API "AUTH" irá retornar o token juntamente com o tipo Autorização correspondente conforme o exemplo abaixo;
 
```
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJCcmFzaASDASDWxwcmV2IHN0b3JlIASDDASDDVCASDInN1YiI6IjEiLCJpYXQiOjE2MDE5Njk0MjYsImV4cCI6MTYwMjA1NTgyNn0.IwqIi5o_Pgjj6x4xc6BPkO4z-HG8OJ32jbV_KCjRiRg",
  "type": "Bearer"
}
```

3. Após recuperar o token é só utiliza-lo no cabeçalho de cada requisição nas API's da aplicação. 


## **Swagger** 

A aplicação possui uma documentação dinâmica do Swagger. Para acessa-la basta clicar no link abaixo:

[Link Heroku](https://microservicesimulator.herokuapp.com/swagger-ui.html)

## **Referências** 

[JWT](https://jwt.io/)

[H2DataBases](https://github.com/h2database/h2database)


