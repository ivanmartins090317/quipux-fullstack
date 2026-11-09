# Prova técnica Fullstack Quipux

Cadastro de pessoas em Java (Spring Boot) com interface React. O parâmetro das rotas `/list/{id}` e `/findNacionalityByPerson/{id}` é um UUID.

## Como rodar

Pré-requisitos: Java 21 e Node 22+.

Backend (perfil `dev`, banco H2 em arquivo):

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

Frontend:

```powershell
cd frontend
npm install
npm run dev
```

- UI: http://localhost:5173
- Swagger: http://localhost:8080/swagger-ui.html
- Console H2 (só dev): http://localhost:8080/h2-console  
  JDBC URL `jdbc:h2:file:./data/prova`, usuário `sa`, senha vazia.

Usuário de demonstração: `avaliador` / `quipux-demo`.

## Perfil prod (Neon)

```powershell
$env:SPRING_PROFILES_ACTIVE = "prod"
$env:DATABASE_URL = "jdbc:postgresql://HOST/neondb?sslmode=require"
$env:DATABASE_USERNAME = "..."
$env:DATABASE_PASSWORD = "..."
$env:AUTH_USERNAME = "avaliador"
$env:AUTH_PASSWORD = "..."
$env:JWT_SECRET = "um-segredo-com-pelo-menos-32-bytes"
cd backend
.\mvnw.cmd spring-boot:run
```

O modelo em `.env.example` mostra a conversão da connection string do Neon. O Spring não lê `.env` sozinho: exporte as variáveis no terminal.

## Endpoints

| Método | Caminho | Auth |
| --- | --- | --- |
| POST | `/registrarName` | pública |
| GET | `/list` | pública |
| GET | `/list/{id}` | pública |
| DELETE | `/list/{id}` | Bearer JWT |
| GET | `/findNacionalityByPerson/{id}` | Bearer JWT |
| POST | `/auth/login` | pública |

## Testes

```powershell
cd backend
.\mvnw.cmd test
```

## Documentação da prova

| Tipo | Onde |
| --- | --- |
| Constituição / fluxo IA | [`AGENTS.md`](./AGENTS.md), [`CHECKLIST-feature.md`](./CHECKLIST-feature.md), [`prompts/`](./prompts/) |
| Specs (SoT) | [`specs/`](./specs/) |
| Docs vivos | [`docs/implementation/`](./docs/implementation/), [`docs/manual-dev/`](./docs/manual-dev/), [`docs/state/PENDENCIAS.md`](./docs/state/PENDENCIAS.md) |
| Call técnica | [`docs/03-files-walkthrough.md`](./docs/03-files-walkthrough.md), [`docs/04-auth-decision.md`](./docs/04-auth-decision.md) |
| Decisões | [`docs/DECISIONS.md`](./docs/DECISIONS.md) |

Rules Cursor: `.cursor/rules/` (workflow, Java, React, specs).
