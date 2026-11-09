# Spec F07: Frontend web UI

- Status: `done`
- Date: 2026-11-09
- Autonomia: `medium`
- Parent: MVP enunciado

## Objective

React SPA: register, list, detail, login, nationality (JWT), delete with confirmation; dev proxy to API.

## Scenarios

- Register shows field errors from API
- Without login, protected actions show message
- With login, nationality and delete succeed

## Done

- [x] Vite + TS + Tailwind
- [x] `api.ts` + session token storage

## Allowed scope

- `frontend/**` (exclude `node_modules`, `dist`)
