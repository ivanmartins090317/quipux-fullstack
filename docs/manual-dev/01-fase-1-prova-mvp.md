# Fase 1 · MVP enunciado

| Status | Spec |
| --- | --- |
| código OK · homologação manual pendente | `specs/2026-11-09-prova-mvp-enunciado.md` |

## O que entrega

API Java + UI React para cadastro de pessoas, consulta de nacionalidade (nationalize.io) e exclusão com JWT.

Não entrega: deploy, Neon obrigatório no dev, gov.br.

---

## Arquitetura resumida

Ver `docs/01-architecture.md`. Mapa para entrevista: `docs/03-files-walkthrough.md`.

---

## Credenciais demo (perfil `dev`)

| Uso | Valor |
| --- | --- |
| Login UI / `POST /auth/login` | `avaliador` / `quipux-demo` |

JWT necessário só em **DELETE** e **GET findNacionalityByPerson**.

---

## Comandos

```powershell
# API
cd backend
.\mvnw.cmd spring-boot:run

# UI (outro terminal)
cd frontend
npm install
npm run dev
```

Testes:

```powershell
cd backend
.\mvnw.cmd test

cd ..\frontend
npm run build
```

URLs: UI http://localhost:5173 · Swagger http://localhost:8080/swagger-ui.html

---

## Homologação manual

- [ ] POST cadastro com CPF mascarado salva 11 dígitos
- [ ] GET list e GET by id sem token
- [ ] DELETE sem token → 401
- [ ] Login → nacionalidade retorna país em português (internet)
- [ ] DELETE com token → 204; GET id → 404
- [ ] Swagger bate com rotas do enunciado
- [ ] UI: fluxo completo sem erros no console

---

## Próxima fase

[../state/PENDENCIAS.md](../state/PENDENCIAS.md)

Registro: [../implementation/F1-prova-mvp.md](../implementation/F1-prova-mvp.md)
