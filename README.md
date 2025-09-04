# Microserviço de Pedidos

## Visão Geral
Microserviço responsável pelo gerenciamento de pedidos do restaurante Sabores Conectados.

## Funcionalidades
- ✅ CRUD completo de pedidos
- ✅ Gerenciamento de itens do pedido
- ✅ Controle de status (EM_ANDAMENTO, PAGO, CANCELADO)
- ✅ Integração com pagamentos
- ✅ Aprovação de pagamentos
- ✅ Integração com Eureka Server
- ✅ API REST completa

## Tecnologias
- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Cloud 2023.0.3**
- **Spring Data JPA**
- **MySQL 8.0**
- **Flyway** (migrações)
- **ModelMapper**
- **Lombok**

## Estrutura do Projeto
```
pedidos/
├── src/main/java/
│   └── br/com/saboresconectados/pedidos/
│       ├── model/               # Entidades JPA
│       ├── dto/                 # Data Transfer Objects
│       ├── repository/          # Repositórios JPA
│       ├── service/             # Lógica de negócio
│       └── controller/          # Controllers REST
├── src/main/resources/
│   ├── application.properties
│   └── db/migration/            # Scripts Flyway
├── src/test/                    # Testes unitários
├── Dockerfile                   # Container Docker
├── pom.xml                      # Dependências Maven
└── README.md                    # Este arquivo
```

## Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.6+
- MySQL 8.0+

### Configuração do Banco
```sql
CREATE DATABASE pedidos;
```

### Execução Local
```bash
cd pedidos
mvn spring-boot:run
```

### Execução com Docker
```bash
docker build -t pedidos-service .
docker run -p 8083:8083 pedidos-service
```

## Endpoints da API

### Base URL
- **Local**: http://localhost:8083/pedidos
- **Gateway**: http://localhost:8084/pedidos

### Principais Endpoints
- `GET /pedidos` - Listar pedidos
- `GET /pedidos/{id}` - Obter pedido por ID
- `POST /pedidos` - Criar novo pedido
- `PUT /pedidos/{id}/status` - Atualizar status
- `PUT /pedidos/{id}/pago` - Aprovar pagamento
- `GET /pedidos/porta` - Verificar porta do serviço

### Exemplo de Uso
```bash
# Listar pedidos
curl http://localhost:8084/pedidos

# Criar novo pedido
curl -X POST http://localhost:8084/pedidos \
  -H "Content-Type: application/json" \
  -d '{
    "dataPedido": "2024-01-15T10:30:00",
    "status": "EM_ANDAMENTO",
    "itens": [
      {
        "produtoId": 1,
        "quantidade": 2,
        "preco": 25.90
      }
    ]
  }'
```

## Modelo de Dados

### Pedido
```json
{
  "id": 1,
  "dataPedido": "2024-01-15T10:30:00",
  "status": "EM_ANDAMENTO",
  "itens": [
    {
      "id": 1,
      "produtoId": 1,
      "quantidade": 2,
      "preco": 25.90
    }
  ]
}
```

### Status de Pedido
- **EM_ANDAMENTO**: Pedido em preparação
- **PAGO**: Pedido pago
- **CANCELADO**: Pedido cancelado
- **ENTREGUE**: Pedido entregue

## Integração com Outros Microserviços
- **Cardápio**: Obtém informações dos produtos
- **Pagamentos**: Processa pagamentos dos pedidos
- **Gateway**: Acessível via roteamento

## Configuração
O microserviço está configurado para:
- **Porta**: 8083
- **Banco**: MySQL (pedidos)
- **Eureka**: Registrado automaticamente
- **Gateway**: Acessível via http://localhost:8084/pedidos

## Desenvolvimento
Para contribuir com o desenvolvimento:
1. Clone o repositório
2. Configure o ambiente de desenvolvimento
3. Execute os testes: `mvn test`
4. Faça suas alterações
5. Execute os testes novamente
6. Faça commit das alterações