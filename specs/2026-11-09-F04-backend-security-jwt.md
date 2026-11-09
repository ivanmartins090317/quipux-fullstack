# Spec F04: JWT security (option 1)

- Status: `done`
- Date: 2026-11-09
- Autonomia: `tight`
- Parent: MVP enunciado

## Objective

`POST /auth/login` issues Bearer JWT; only `DELETE /list/{id}` and `GET /findNacionalityByPerson/{id}` require authentication.

## Scenarios

- Missing/invalid token on protected routes → 401 JSON
- Public routes work without token
- Demo credentials from configuration (dev only)

## Done

- [x] `SecurityConfig`, `JwtService`, filter, `AuthController`
- [x] Disable default Spring generated user password

## Allowed scope

- `backend/**/infra/auth/**`
- `SecurityConfig` and login DTO only elsewhere
