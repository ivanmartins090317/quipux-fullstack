# Fase 1 · MVP enunciado Quipux

| Campo | Valor |
| --- | --- |
| **Status** | concluída (código) · homologação manual pendente |
| **Spec** | `specs/2026-11-09-prova-mvp-enunciado.md` |
| **Decisões** | `docs/DECISIONS.md` |

## Objetivo

Atender o teste Fullstack: cadastro, listagem, busca por UUID, exclusão autenticada, nacionalidade via API externa e interface web.

## Entregue

### Backend (`backend/`)

| Área | Conteúdo |
| --- | --- |
| domain | `Person`, exceções, helpers documento/nome |
| application | ports + 6 use cases |
| infra | controllers literais, JPA/H2+Postgres, JWT, NationalizeClient, springdoc |
| testes | `ApplicationUseCasesTest`, `PersonApiIntegrationTest`, context load |

### Frontend (`frontend/`)

| Área | Conteúdo |
| --- | --- |
| UI | cadastro, lista, detalhe, login, nacionalidade, exclusão |
| api | `fetch` + Bearer + erros tipados |

### Documentação estática (prova)

- `docs/00-enunciado-checklist.md`, `01-architecture.md`, `03-files-walkthrough.md`, `04-auth-decision.md`, `02-build-journal.md`

## Evidências de Done

| Comando | Resultado (2026-11-09) |
| --- | --- |
| `backend/mvnw.cmd test` | 10 testes, BUILD SUCCESS |
| `frontend/npm run build` | OK |

## Pendências

Ver `docs/state/PENDENCIAS.md` e homologação em `docs/manual-dev/01-fase-1-prova-mvp.md`

## Manual do dev

[`docs/manual-dev/01-fase-1-prova-mvp.md`](../manual-dev/01-fase-1-prova-mvp.md)
