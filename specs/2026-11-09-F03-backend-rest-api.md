# Spec F03: REST API and validation

- Status: `done`
- Date: 2026-11-09
- Autonomia: `medium`
- Parent: MVP enunciado

## Objective

HTTP adapters with literal enunciado paths, Bean Validation on bodies, UUID path params, JSON error contract, springdoc OpenAPI.

## Scenarios

- POST registrar returns 201; invalid email 400
- GET list public; GET by id 404 when missing
- Invalid UUID on path returns 400

## Done

- [x] Controllers + DTOs + `GlobalExceptionHandler`
- [x] Swagger UI at `/swagger-ui.html`

## Allowed scope

- `backend/src/main/java/com/quipux/prova/infra/http/**`
- `backend/src/main/java/com/quipux/prova/infra/config/OpenApiConfig.java`
