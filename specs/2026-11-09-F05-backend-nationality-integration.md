# Spec F05: Nationality integration

- Status: `done`
- Date: 2026-11-09
- Autonomia: `medium`
- Parent: MVP enunciado

## Objective

Call `https://api.nationalize.io/?name=` with timeouts; return country name in Portuguese (not ISO only); map external failures to 502.

## Scenarios

- Person not found still 404 before external call
- External timeout/error → 502
- Empty country list → 422

## Done

- [x] `NationalizeClient` implements `NationalityClient` port
- [x] `CountryNameResolver` for pt-BR display names

## Allowed scope

- `backend/src/main/java/com/quipux/prova/infra/nationalize/**`
