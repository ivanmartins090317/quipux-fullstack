# Pendências · quipux-prova-fullstack

Última revisão: **2026-11-09**

---

## Fase 1 · MVP enunciado (código)

Referência: `specs/2026-11-09-prova-mvp-enunciado.md` · `docs/implementation/F1-prova-mvp.md`

- [x] Endpoints REST literais + validações
- [x] JWT em DELETE e findNationality
- [x] nationalize.io + nome do país em pt-BR
- [x] React consumindo API
- [x] Swagger springdoc
- [x] Testes Maven (10)

Homologação manual pendente:

- [ ] Demo completa gravada ou ensaiada (cadastro → nacionalidade → delete)
- [ ] README + link Git para entrega terça 10/11
- [ ] Releitura `docs/03-files-walkthrough.md` antes da call ~13/11

---

## Fase 2 · Entrega e opcionais

- [ ] Primeiro commit + push remoto
- [ ] Perfil `prod` testado com Neon (URL JDBC)
- [ ] Flyway ou migration versionada (hoje `ddl-auto: update`)
- [ ] Deploy Heroku + Vercel (opcional pós-Git)

---

## Dívida técnica transversal

- [ ] Credenciais demo apenas em `dev`/`test` (prod exige env)
- [ ] Desligar Swagger/H2 console em prod se subir deploy
