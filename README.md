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
