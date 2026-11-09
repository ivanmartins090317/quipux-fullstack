# Checklist: 1 feature

## Antes de pedir código

- [ ] Feature nomeada
- [ ] Autonomia (`tight` / `medium` / `loose`)
- [ ] Research (`prompts/01-research.md`)
- [ ] Plano (`prompts/02-plano.md`)
- [ ] Spec em `specs/` com status `aprovada`

## Durante

- [ ] Escopo da Spec respeitado
- [ ] Paths críticos só com pedido
- [ ] Back: use case sem vazar infra
- [ ] Front: mudanças via `api.ts` quando for contrato HTTP

## Done

- [ ] `backend/mvnw.cmd test`
- [ ] `frontend/npm run build`
- [ ] Docs vivos (implementation, manual-dev, PENDENCIAS)
- [ ] Spec → `done` se aplicável
- [ ] Relatório `AGENTS.md`

## Review humano

- [ ] `medium/loose`: Spec vs resultado
- [ ] `tight` ou auth: ler diff
- [ ] Commit pequeno (quando autorizado)
