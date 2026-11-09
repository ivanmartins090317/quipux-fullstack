# Spec F06: Backend automated tests

- Status: `done`
- Date: 2026-11-09
- Autonomia: `medium`
- Parent: MVP enunciado

## Objective

Prove validations and auth matrix without live nationalize in CI; context load smoke test.

## Scenarios covered in tests

- Invalid email, invalid UUID, 401/409/204/404 flow
- Nationalize mocked; 502 when client throws

## Done

- [x] `ApplicationUseCasesTest`
- [x] `PersonApiIntegrationTest`
- [x] `application-test.yml` profile

## Allowed scope

- `backend/src/test/**`
