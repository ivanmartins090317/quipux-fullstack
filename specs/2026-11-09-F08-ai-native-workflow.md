# Spec F08: AI-native workflow and living docs

- Status: `done`
- Date: 2026-11-09 (commit **#1** in repo history)
- Autonomia: `loose`
- Parent: internal process (Quipux delivery)

## Objective

Bootstrap **LLM-assisted development** before any product spec or code: `AGENTS.md`, Cursor rules, prompts, close-phase skill, and empty living-doc folders (`implementation/`, `manual-dev/`, `state/`).

## Scenarios

- New feature must have spec before code (per `AGENTS.md`)
- Phase closure updates three living doc folders
- Done commands: `mvnw test` + `npm run build`

## Done

- [x] `.cursor/rules`, `prompts/`, `CHECKLIST-feature.md`
- [x] `docs/implementation`, `docs/manual-dev`, `docs/state`

## Allowed scope

- `AGENTS.md`, `.cursor/**`, `prompts/**`, `CHECKLIST-feature.md`
- `docs/implementation/**`, `docs/manual-dev/**`, `docs/state/**`, `docs/WORKFLOW-AI-NATIVE.md`
