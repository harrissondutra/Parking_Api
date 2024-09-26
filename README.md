
# <img align="center" alt="Java" height="80" width="100" src="https://pngimg.com/uploads/parking/parking_PNG71.png"> Api Parking

## Versão Graphql:

 - Sem relatório momentâneamente
# [Graphiql Playground aqui](https://parkinggraphql-production.up.railway.app/graphiql) <br />

---

---
## Versão Api Rest:

### ***Entidades:*** <br />
Estabelecimentos<br />
Veículos<br />
Controle de Acesso<br />
Endpoints para Dashboard ou Relatórios<br />
<br />

## ***Webservices:***

***Estabelecimentos:***
- Cadadastro
- Busca principal filtrada
- Buscas filtrada por id
- "Soft delete" Para exclusão de estabelecimento
<br />

***Veículos:*** <br />
- Cadastro
- Alteração
- Delete
- Busca geral
- Busca por Id
- Busca por Placa
- Autenticação
<br />

***Controle de Acesso:*** <br />
- Controle de entrada/Saída
<br />

***Relatório:*** <br />
- Contagem Total de Veículos
- Contagem de Veículos por Tipo
- Contagem de Veículos por Dia
- Contagem de Veículos por Hora
- Contagem de Veículos por Tipo e Hora
- Contagem de Entradas de Veículos
- Contagem de Saídas de Veículos
- Contagem de Entradas de Veículos por Hora
- Contagem de Saídas de Veículos por Hora
- Quantidade de veículos por Mês
- Quantidade de veículos por Ano
<br />

*Documentação online - testes liberados com autenticação:* <br />
[Documentação da API via Swagger Aqui](https://parkingapi-production-0b39.up.railway.app/swagger-ui/index.html)<br /><br />
*Em caso de execução em ambiente local, gentileza utilizar: http://localhost:8080/swagger-ui/index.html*


### ***Tecnologias utilizadas:***
- Linguagem Java  <br />
- Framework Spring
- Postgres  <br />
- Deploy: Railway <br />
- Documentação via Swagger


<div style="display: inline_block">
   <img align="center" alt="Java" height="70" width="40" src="https://seeklogo.com/images/J/java-logo-7833D1D21A-seeklogo.com.png">
   <img align="center" alt="Spring" height="40" width="40" src="https://github.com/harrissondutra/harrissondutra/blob/main/.img/logo-spring.png">
   <img align="center" alt="Graphql" height="70" width="140" src="https://cdn.icon-icons.com/icons2/2699/PNG/512/graphql_logo_icon_171045.png">
   <img align="center" alt="Postgres" height="40" width="40" src="https://github.com/harrissondutra/harrissondutra/blob/main/.img/postgresql_logo_icon_170835.png">
   <img align="center" alt="Railway" height="50" width="50" src="https://images.crunchbase.com/image/upload/c_pad,f_auto,q_auto:eco,dpr_1/h3m0hmstlq9maq7t8tyc">
   <img align="center" alt="Swagger" height="40" width="180" src="https://raw.githubusercontent.com/swagger-api/swagger.io/wordpress/images/assets/SWE-logo-clr.png"> 
</div>

---

### 1. GraphQL (Implementação BFF - Backend For Frontend)

Utilize o playground para testar as queries e mutations. <br />
clique no link abaixo:
## [Graphiql Playground aqui](https://parkinggraphql-production.up.railway.app/graphiql) <br />


##### ***Queries:***
```graphql
  accessControls: [AccessControl]
  findByVehiclePlate(vehiclePlate: String): AccessControl
  establishmentById(id: ID): Establishment
  establishments: [Establishment]
  vehicleById(id: ID): Vehicle
  vehicles: [Vehicle]
```

##### ***Mutations:***
```graphql
- Access Control
registerEntry(plate: String, type: VehicleType,establishmentId: ID): AccessControl
registerExit(plate: String): AccessControl
createAccessControl(establishmentId: ID): AccessControl

- Establishment
addEstablishment(establishmentInput: EstablishmentInput): Establishment
updateEstablishment(establishmentId: ID, establishmentInput: EstablishmentInput): Establishment
deleteEstablishment(establishmentId: ID): Establishment
changeStatusEstablishment(establishmentId: ID): Establishment

- Vehicle
addVehicle(vehicleInput: VehicleInput): Vehicle
updateVehicle(vehicleId: ID,vehicleInput: VehicleInput): Vehicle
deleteVehicle(vehicleId: ID): Vehicle
changeStatusVehicle(vehicleId: ID): Vehicle
```

#### Clique no link abaixo para o github da versão Graphql:
### [Repositório Github](https://github.com/harrissondutra/Parking_Graphql)
