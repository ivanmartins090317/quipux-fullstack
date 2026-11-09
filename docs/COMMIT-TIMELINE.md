# Commit timeline (spec-driven delivery)

Suggested narrative for reviewers. Times are **BRT** (Mon 2026-11-09 → Tue 2026-11-10).

| # | When (BRT) | Spec | Commit message (English) |
| --- | --- | --- | --- |
| **1** | **18:00** | **F08** | `chore: add AI-native workflow foundation for LLM-assisted development` |
| 2 | 18:30 | MVP + F01–F07 | `docs(spec): add spec-driven feature specifications` |
| 3 | 19:00 | MVP | `docs: add decisions and enunciado traceability` |
| 4 | 19:30 | F01 | `chore(backend): bootstrap Spring Boot 4.1 monorepo scaffold` |
| 5 | 20:00 | F01 | `feat(backend): add domain models and application use cases` |
| 6 | 20:30 | F02 | `feat(backend): add JPA persistence and repository adapter` |
| 7 | 21:00 | F03 | `feat(backend): expose REST endpoints with validation and error handling` |
| 8 | 21:30 | F04 | `feat(backend): add JWT auth for protected routes` |
| 9 | 22:00 | F05 | `feat(backend): integrate nationality lookup via nationalize.io` |
| 10 | 22:30 | F06 | `test(backend): add use case and API integration tests` |
| 11 | 23:15 | F07 | `feat(frontend): add React UI consuming assessment APIs` |
| 12 | 23:45 | — | `chore: add environment template and root documentation` |
| 13 | 09:00 Tue | F08 close | `docs: add implementation record, walkthrough, and commit timeline` |

## Why F08 is commit #1

The LLM contract (`AGENTS.md`, Cursor rules, prompts, living-doc folders) must exist **before** feature specs and code so every change follows spec-driven development.

## Build compiles at these commits

| After # | Compiles? |
| --- | --- |
| 1–3 | No application code |
| 5–9 | Backend incomplete until HTTP (#7) |
| **10** | `backend/mvnw.cmd test` green |
| **11** | + `frontend/npm run build` |

## Push

```powershell
cd C:\projetos\quipux-prova-fullstack
git remote add origin <URL>
git push -u origin main
```
