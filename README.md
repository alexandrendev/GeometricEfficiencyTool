
## Requisitos

Para subir a aplicação, é necessário ter o

**docker** e o **docker-compose-v2** instalados.

Use o seguinte comando para instalá-los:

```
sudo apt install docker.io && sudo apt install docker-compose-v2
```

---

## Configurando o Ambiente

Agora você precisará do seguinte arquivo **docker-compose.yaml**:

```yaml
networks:
  lafis-net:
    driver: bridge

services:
  mongodb:
    image: mongo:6.0
    container_name: mongodb
    restart: unless-stopped
    ports:
      - "27017:27017"
    volumes:
      - mongodb_data:/data/db
    environment:
      MONGO_INITDB_ROOT_USERNAME: root
      MONGO_INITDB_ROOT_PASSWORD: example
      MONGO_INITDB_DATABASE: geometricEfficiency
    networks:
     - lafis-net
    healthcheck:
      test: echo 'db.runCommand("ping").ok' | mongosh -u root -p example --quiet || exit 1
      interval: 30s
      timeout: 10s
      retries: 3

  backend:
   image: ghcr.io/alexandrendev/lafis-backend/app-lafis:latest
   container_name: backend
   restart: unless-stopped
   environment:
     MONGO_URI: mongodb://root:example@mongodb:27017/geometricEfficiency?authSource=admin
   networks:
     - lafis-net
   ports:
     - "8080:8080"
   depends_on:
     mongodb:
       condition: service_healthy
   healthcheck:
     test: ["CMD", "curl", "-f", "http://localhost:8080/health"]
     interval: 30s
     timeout: 10s
     retries: 3

  frontend:
    image: ghcr.io/alexandrendev/lafis-frontend/app:latest
    container_name: front
    restart: unless-stopped
    ports:
      - "4200:80"
    networks:
      - lafis-net
    depends_on:
      - backend

volumes:
  mongodb_data:
```

Uma vez que você tem o `docker.io`, `docker-compose-v2` e o `docker-compose.yaml` em sua máquina, o próximo passo é abrir um terminal e navegar até o diretório onde o arquivo está localizado:
```bash
cd caminho/para/o/arquivo
```

Agora, basta rodar o seguinte comando e aguardar até que todos os serviços tenham subido:
```bash
sudo docker compose up #sudo é necessário, caso seu usuário não esteja no grupo de permissões do docker
```

Você saberá que está tudo OK quando vir algo parecido com a imagem abaixo no seu terminal:
```bash
backend | 2025-09-04T22:59:30.408Z INFO 1 [GeometricEfficiencyTool] [main] c.l.G.GeometricEfficiencyToolApplication Started GeometricEfficiencyToolApplication in 3.073 seconds (process running for 3.599)
```

A seguinte linha confirma que o último serviço está rodando e está tudo pronto para acessar a aplicação9:

`backend | [ 2025-09-04T22:59:30.408Z INFO 1 [GeometricEfficiencyTool] main] c.l.G.GeometricEfficiency ToolApplication Started GeometricEfficiency ToolApplication in 3.073 seconds (process running for 3.599)` 10

---
# Rodando no Windows com Docker Compose

## Passo 1 – Instalar Docker Desktop
- Baixe e instale o [Docker Desktop for Windows](https://www.docker.com/products/docker-desktop/).  
- Ele já vem com **Docker Engine** e **docker compose v2** embutidos.  
- Após a instalação, abra o **Docker Desktop** e garanta que ele está rodando (o ícone deve aparecer na bandeja do sistema).

---

## Passo 2 – Preparar o `docker-compose.yaml`
Salve o arquivo `docker-compose.yaml` exatamente como fornecido, em uma pasta no seu PC, por exemplo:  
`C:\Users\seu-usuario\lafis\`

## Passo 3 – Abrir terminal
Abra o **PowerShell** ou **Prompt de Comando** e navegue até a pasta onde o arquivo está:  
```powershell
cd C:\Users\seu-usuario\lafis\
```

## Passo 4 – Subir os containers
No Windows não é necessário sudo:
```powershell
docker compose up
```

---

## Acessando a Aplicação

Uma vez que todos os passos acima foram executados com sucesso, você poderá acessar o sistema em seu navegador na URL

**localhost:4200**.

A partir daí, é só registrar uma conta e fazer login no sistema!

----

# GeometricEfficiencyTool

## Endpoints

### Iniciar Simulação
**POST** `/simulation/start?simulationId={$ID}`

**Parâmetros:**
- `simulationId` (query param, string) - ID da simulação.

**Respostas:**
- `200 OK`: Retorna a simulação se a execução for bem-sucedida.
- `404 NOT FOUND`: Se a simulação não for encontrada.
- `409 CONFLICT`: Se a simulação já foi executada.

---

### Buscar Simulação por ID
**GET** `/simulation/id?simulationId={$ID`

**Parâmetros:**
- `simulationId` (query param, string) - ID da simulação.

**Respostas:**
- `200 OK`: Retorna a simulação correspondente.

---

### Criar Nova Simulação
**POST** `/simulation/new`

**Corpo da requisição:**
```json
{
  "emissions": number,
  "sourceHeight": number
}

```
**Respostas:**
- `201 CREATED`: Retorna a simulação criada.

---

### Criar Novo Contexto
**POST** `/simulation/new-context`


**Corpo da requisição:**
```json
{
  "emissions": number,
  "sourceHeight": number,
  "apertureType": "String",
  "aperture": Aperture,
  "sourceType": "String",
  "source": Source
}

  ```

**Respostas:**
- `201 CREATED`: Retorna a simulação criada com o contexto definido.

---

### Definir Abertura Retangular
**PATCH** `/simulation/rectangular`

**Corpo da requisição:**
```json
{
  "simulationId": "String",
  "width": double,
  "depth": double,
  "height": double
}

  ```

**Respostas:**
- `200 OK`: Retorna a simulação atualizada.

---

### Definir Abertura Circular
**PATCH** `/simulation/circular`

**Corpo da requisição:**
```json
{
  "simulationId": "String",
  "radius": double,
  "height": double
}

  ```

**Respostas:**
- `200 OK`: Retorna a simulação atualizada.

---

### Definir Fonte Cilíndrica
**PATCH** `/simulation/source/cylindrical`

**Corpo da requisição:**
```json
{
  "simulationId": "String",
  "sourceHeight": double,
  "sourceRadius": double
}

  ```

**Respostas:**
- `200 OK`: Retorna a simulação atualizada.

---

### Definir Fonte Cúbica
**PATCH** `/simulation/source/cuboid`

**Corpo da requisição:**
```Json
{
  "simulationId": "String",
  "sourceHeight": double,
  "sourceWidth": double,
  "sourceDepth": double
}

  ```

**Respostas:**
- `200 OK`: Retorna a simulação atualizada.

---

### Definir Fonte Esférica
**PATCH** `/simulation/source/spherical`

**Corpo da requisição:**
```json
{
  "simulationId": "String",
  "sourceRadius": double
}

```

**Respostas:**
- `200 OK`: Retorna a simulação atualizada.

---

### Listar Todas as Simulações
**GET** `/simulation/all`

**Respostas:**
- `200 OK`: Retorna uma lista de todas as simulações.

---

### Listar Simulações em Execução
**GET** `/simulation/running`

**Respostas:**
- `200 OK`: Retorna uma lista de simulações em execução.
