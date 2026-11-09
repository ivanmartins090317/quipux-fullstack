# AGENTS.md · Prova técnica Quipux

Constituição do monorepo para agentes de IA. Alterar só com aprovação explícita do Ivan.

Spec **F08** / primeiro commit do repositório: estrutura LLM (este arquivo, rules, prompts, docs vivos).

## Stack

| Camada | Tecnologia |
| --- | --- |
| API | Java 21, Spring Boot 4.1.2 (`spring-boot-starter-webmvc`), Validation, Security, JPA |
| Persistência | H2 arquivo (`dev`), PostgreSQL JDBC (`prod` / Neon) |
| Integração | `RestClient` → nationalize.io |
| Docs API | springdoc-openapi 3.2 → `/swagger-ui.html` |
| Front | React 19, Vite 7, TypeScript, Tailwind 4 em `frontend/` |
| Testes back | JUnit 5, MockMvc, use cases sem Spring |

Package manager front: **npm**. Back: **Maven Wrapper** (`backend/mvnw.cmd`).

## Arquitetura (obrigatória no back)

```text
backend/src/main/java/com/quipux/prova/
  domain/           # modelos, exceções de negócio (sem Spring)
  application/      # ports + use cases
  infra/            # http, persistence, auth, nationalize, config
frontend/src/
  api.ts            # cliente HTTP + Bearer
  components/
  types.ts
```

Regra: **use case não importa JPA, Security nem RestClient**. Controller não acessa `JpaRepository` direto.

Paths REST literais do enunciado (não renomear): `/registrarName`, `/list`, `/findNacionalityByPerson/{id}`, etc.

## Estrutura de documentação

```text
specs/                          # Source of Truth por feature (aprovada)
docs/
  DECISIONS.md                  # decisões fixas da prova
  00-enunciado-checklist.md     # checklist PDF
  03-files-walkthrough.md       # mapa para call técnica
  implementation/               # o que foi entregue por fase
  manual-dev/                   # como rodar e homologar
  state/PENDENCIAS.md           # implementado vs homologação
prompts/                        # copy-paste do workflow AI-native
```

Referência do kit: `segundo-cerebro/10 Dev/Referencias/workflow-ai-native/`

## Pode

- Editar `backend/`, `frontend/`, `docs/implementation/`, `docs/manual-dev/`, `docs/state/`, `specs/` (draft ou com pedido)
- Criar testes alinhados à Spec aprovada
- Rodar `backend/mvnw.cmd test`, `frontend/npm run build`
- Ler vault e `docs/*` para contexto da prova

## Não pode

- Alterar este `AGENTS.md` sem pedido
- Alterar Spec com status **aprovada** ou **done** sem pedido
- `git push`, merge em `main`, deploy, commit (salvo pedido explícito)
- Nova dependência Maven/npm sem aprovação
- Commitar `.env` ou segredos reais
- Microserviços, trocar stack (Node no back), renomear rotas do enunciado

## Paths críticos (autonomia **tight**)

Só com pedido explícito + Spec aprovada + review humano:

- `backend/**/infra/auth/**`, `SecurityConfig.java`, `JwtService.java`
- `backend/src/main/resources/application*.yml`
- `.env`, `.env.example` (valores de prod)
- `backend/pom.xml` (dependências)
- `frontend/src/api.ts` (contrato HTTP / token)

## Autonomia por tarefa

- `tight`: auth, segurança, contrato REST, persistência prod
- `medium`: **default** — features novas, UI, testes
- `loose`: docs, copy, polish sem mudar comportamento

## Fluxo obrigatório por feature

1. Research (sem código) → `prompts/01-research.md`
2. Plano curto → `prompts/02-plano.md`
3. Spec em `specs/` → **aprovação humana** → `prompts/03-spec.md`
4. Implementar só o escopo da Spec → `prompts/04-implementar.md`
5. Fechar docs vivos → skill `.cursor/skills/close-phase/SKILL.md`
6. Verificar Done → `prompts/05-verificar.md`

Spec aprovada = Source of Truth.

## Done (comandos objetivos)

Só declarar Done quando **todos** passarem:

```powershell
cd backend
.\mvnw.cmd test

cd ..\frontend
npm run build
```

E também:

- Cenários da Spec atendidos
- Docs vivos atualizados (implementation, manual-dev, PENDENCIAS)
- Resumo: arquivos, comandos, riscos, diff crítico sim/não

## DoD de documentação (ao fechar fase)

| Documento | Conteúdo |
| --- | --- |
| `docs/implementation/F{N}-*.md` | Entregáveis + evidências dos comandos |
| `docs/manual-dev/{NN}-fase-{N}-*.md` | Fluxos, credenciais demo, homologação |
| `docs/state/PENDENCIAS.md` | `[x]` código, `[ ]` homologação manual |
| Índices `docs/implementation/README.md` e `docs/manual-dev/README.md` | Status da fase |

## Como reportar ao final

```md
## Resultado
- Autonomia usada:
- Spec:
- Comandos Done:
- Docs vivos (links):
- Arquivos alterados:
- Riscos / dúvidas:
- Diff crítico para review humano: sim/não
```
