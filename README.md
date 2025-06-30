# 🧪 Testes Automatizados - PetStore Swagger API

Este repositório contém uma suíte de testes automatizados utilizando a biblioteca **RestAssured** em Java para validar endpoints da API pública [Petstore Swagger](https://petstore.swagger.io/).

---

## ✅ Escopo dos Testes

Os testes cobrem operações básicas de **CRUD** e **busca** nas entidades `Pet` e `Order` da PetStore API.

---

## 🔧 Tecnologias Utilizadas

- Java
- JUnit 4
- RestAssured
- API: [Petstore Swagger](https://petstore.swagger.io/v2)

---

## 📂 Estrutura dos Casos de Teste

### 1. `testAdicionarPet`

**Objetivo:** Verificar se é possível adicionar um novo pet com sucesso.  
**Requisição:** `POST /pet`  
**Validações:**
- Código de status 200
- O campo `id` do pet criado não deve estar vazio

---

### 2. `testEncontrarPorStatus`

**Objetivo:** Buscar todos os pets com status `available`.  
**Requisição:** `GET /pet/findByStatus?status=available`  
**Validações:**
- Código de status 200
- Todos os pets retornados devem ter status igual a `available`

---

### 3. `testRemoverPet`

**Objetivo:** Verificar se é possível remover um pet da base de dados.  
**Requisições:**
1. `POST /pet` — cria o pet  
2. `DELETE /pet/{petId}` — remove o pet  
**Validações:**
- Código de status 200 em ambas as requisições  
- A resposta da exclusão deve conter o ID do pet deletado no campo `message`

---

### 4. `testEfetuarPedido`

**Objetivo:** Verificar se um pedido pode ser criado com sucesso.  
**Requisição:** `POST /store/order`  
**Validações:**
- Código de status 200  
- O campo `id` do pedido criado não deve estar vazio

---

### 5. `testRemoverPedido`

**Objetivo:** Verificar se um pedido pode ser deletado com sucesso.  
**Requisições:**
1. `POST /store/order` — cria o pedido  
2. `DELETE /store/order/{orderId}` — remove o pedido  
**Validações:**
- Código de status 200 em ambas as requisições  
- A resposta da exclusão deve conter o ID do pedido deletado no campo `message`

---

## 📌 Observações

- Todos os testes utilizam o endpoint base: `https://petstore.swagger.io/v2`  
- Os dados de entrada são estáticos e podem ser adaptados para maior robustez  
- Os testes são independentes e podem ser executados em paralelo com o uso de configurações apropriadas

---

## ▶️ Como Executar os Testes

1. Clone o repositório  
2. Importe como projeto Maven/Gradle (ou configure dependências manualmente)  
3. Execute os testes via sua IDE ou com o comando:

```bash
mvn test
```

---

## 🧹 Melhorias Futuras

- Aplicação de POJOs para modelar e organizar o código 
- Parametrização de dados com DataProvider  
- Integração com CI (GitHub Actions, Jenkins etc.)  
- Validação de contratos com JSON Schema

---

## 👨‍💻 Autor
Desenvolvido por Clauthucio Chaves