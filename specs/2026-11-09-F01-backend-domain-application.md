# Spec F01: Backend domain and application

- Status: `done`
- Date: 2026-11-09
- Autonomia: `medium`
- Parent: `2026-11-09-prova-mvp-enunciado.md`

## Objective

Pure business layer: person aggregate, document helpers, domain exceptions, repository/nationality/auth ports, and use cases without Spring Web or JPA.

## Scenarios

- Register normalizes CPF digits and rejects duplicate document
- Get/Delete throw not found when UUID absent
- Find nationality uses first name token only
- Login rejects invalid credentials

## Done

- [x] Packages `domain/` and `application/`
- [x] No framework imports in domain

## Allowed scope

- `backend/src/main/java/com/quipux/prova/domain/**`
- `backend/src/main/java/com/quipux/prova/application/**`
