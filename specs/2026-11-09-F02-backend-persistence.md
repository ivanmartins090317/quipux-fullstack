# Spec F02: Backend persistence

- Status: `done`
- Date: 2026-11-09
- Autonomia: `tight`
- Parent: MVP enunciado

## Objective

Persist `Person` via JPA implementing `PersonRepository` port; support H2 (dev) and PostgreSQL JDBC (prod).

## Scenarios

- Save generates UUID at application layer
- Unique constraint on `documento` maps to domain duplicate exception
- findAll ordered for stable listing

## Done

- [x] `PersonEntity`, `PersonJpaRepository`, `PersonRepositoryAdapter`
- [x] Profiles `dev` / `prod` in YAML

## Allowed scope

- `backend/src/main/java/com/quipux/prova/infra/persistence/**`
- `backend/src/main/resources/application*.yml`
