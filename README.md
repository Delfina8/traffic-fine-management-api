# Traffic Fine Management

# Descrição do Projeto
O Traffic Fine Management API é uma aplicação desenvolvida em Java utilizando o framework Spring Boot. O projeto gerencia um sistema de multas e apreensão de veículos, permitindo operações como multar e apreender veículos, adicionar, atualizar, buscar e excluir.

# Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- Springdoc-OpenApi
- Lombok
- MySQL
- Flyway

# API Endpoints
### Proprietário
GET /proprietarios/{proprietarioId}

GET /proprietarios

POST /proprietarios

PUT /proprietarios/{proprietarioId}

DELETE /proprietarios/{proprietarioId}
```
{
  "nome": "string",
  "email": "string",
  "telefone": "string"
}
```

### Veículo
GET /veiculos

GET /veiculos/{veiculoId}

POST /veiculos

PUT /veiculos/{veiculoId}/apreensao

DELETE /veiculos/{veiculoId}/apreensao

```
{
	"proprietario": {
        "id": 1
    },
    "marca": "string",
    "modelo": "string",
    "placa": "ABC9F26"
}
```

### Autuação
GET /veiculos/{veiculoId}/autuacoes

POST /veiculos/{veiculoId}/autuacoes
```
{
  "descricao": "string",
  "valorMulta": "450"
}
```
