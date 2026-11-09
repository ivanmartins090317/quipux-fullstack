# Spec: MVP enunciado Quipux

Umbrella spec. Feature slices: see [README.md](./README.md) (F01–F08).

- Status: `done`
- Data: 2026-11-09
- Autonomia: `medium` (auth/persistência críticos em `tight`)
- Projeto: quipux-prova-fullstack
- Pedido original: Teste Desenvolvedor Fullstack (Java + persistência + APIs + UI)

## 1. Objetivo

Cadastrar pessoas (documento, nome, sobrenome, e-mail), listar e buscar por UUID, excluir com controle de acesso, estimar nacionalidade pelo primeiro nome via API externa, e oferecer interface web que consuma essas operações.

## 2. Fora de escopo

Microserviços, Angular, gov.br, deploy obrigatório, auth global em todas as rotas.

## 3. Atores e pré-condições

- Avaliador ou candidato na demo
- API rodando (H2 dev); internet para nationalize na homologação real

## 4. Comportamento esperado

### Cenário 1: cadastro e listagem

- Dado: dados válidos com documento 11 dígitos (com ou sem máscara)
- Quando: cadastro é solicitado
- Então: pessoa aparece na listagem com UUID; e-mail inválido retorna erro

### Cenário 2: busca e UUID inválido

- Dado: UUID existente ou texto inválido
- Quando: busca por id
- Então: 200 com dados ou 404; UUID malformado → 400

### Cenário 3: exclusão protegida

- Dado: pessoa cadastrada
- Quando: exclusão sem sessão JWT
- Então: 401; com JWT válido → removida (204) e busca seguinte 404

### Cenário 4: nacionalidade protegida

- Dado: pessoa cadastrada e JWT válido
- Quando: consulta de nacionalidade
- Então: resposta com nome do país (não só ISO); sem JWT → 401

### Cenário 5: documento duplicado

- Dado: mesmo documento já cadastrado
- Quando: novo cadastro
- Então: conflito (409)

## 5. Regras de negócio

- Parâmetro de rota: UUID
- JWT só em DELETE `/list/{id}` e GET `/findNacionalityByPerson/{id}` (grafia do enunciado)
- Primeiro nome usado na API nationalize
- Documento único no banco

## 6. Critérios de Done

- [x] Cenários cobertos por testes e/ou homologação documentada
- [x] `backend/mvnw.cmd test`
- [x] `frontend/npm run build`
- [x] Docs vivos F1 + PENDENCIAS
- [x] Swagger publicado

## 7. Escopo permitido (entrega F1)

Todo `backend/` e `frontend/` conforme implementado; decisões em `docs/DECISIONS.md`.

## 8. Aprovação

- Aprovado por: Ivan (decisões 2026-11-09)
- Data: 2026-11-09
- Observações: entrega alvo Git terça 10/11 pós-almoço
