---
name: close-phase
description: >-
  Fechar fase no monorepo Quipux: evidências Maven/npm, depois
  docs/implementation, docs/manual-dev e docs/state/PENDENCIAS.md.
  Use ao concluir feature, fechar fase ou antes de marcar spec como done.
---

# Fechamento de fase · quipux-prova-fullstack

## Pré-requisito técnico

Registrar evidências:

```powershell
cd backend
.\mvnw.cmd test

cd ..\frontend
npm run build
```

## Checklist de documentação (obrigatório)

1. `docs/implementation/F{N}-*.md` + atualizar `docs/implementation/README.md`
2. `docs/manual-dev/{NN}-fase-{N}-*.md` + atualizar `docs/manual-dev/README.md`
3. `docs/state/PENDENCIAS.md`: marcar implementado; listar homologação manual pendente
4. Spec: status `done` só após itens acima

## Ordem

1. Comandos verdes
2. Implementation + README
3. Manual-dev + README
4. PENDENCIAS.md
5. Reportar links ao humano (formato em `AGENTS.md`)

## Não fazer

- Alterar Spec sem pedido explícito
- Marcar homologação manual como feita sem o Ivan rodar
- `git commit` / `push` sem pedido

Constituição: `AGENTS.md`
