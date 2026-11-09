# Workflow AI-native neste repo

Kit adaptado de `10 Dev/Referencias/workflow-ai-native`.

## Arquivos-chave

| Arquivo | Função |
| --- | --- |
| `AGENTS.md` | Constituição (stack, pode/não pode, Done) |
| `.cursor/rules/*.mdc` | Workflow always-on + Java + React + specs |
| `.cursor/skills/close-phase/SKILL.md` | Fechar fase com docs vivos |
| `specs/` | Source of Truth por feature |
| `prompts/01` … `05` | Sequência no chat |
| `CHECKLIST-feature.md` | Checklist diário |

## Fluxo

0. **F0 primeiro** no Git (workflow + AGENTS) → depois specs → depois código.
1. Research → Plano → Spec **aprovada** → Implementar → Docs vivos → Verificar Done.

## Fase atual

- **F1 MVP:** `specs/2026-11-09-prova-mvp-enunciado.md` (status `done` em código)
- Homologação: `docs/manual-dev/01-fase-1-prova-mvp.md`
- Pendências: `docs/state/PENDENCIAS.md`

Próximas features (ex.: Neon prod, deploy): nova Spec em `specs/` antes de codar.
