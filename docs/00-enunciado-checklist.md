# Checklist do enunciado

| Pedido | Onde | Validação |
| --- | --- | --- |
| POST `/registrarName` | `PersonController.register` | documento (11 dígitos), nome, sobrenome, e-mail. Documento único devolve 409. |
| GET `/list` | `PersonController.list` | lista pode ser vazia. Ordenada por nome. |
| GET `/list/{id}` | `PersonController.get` | UUID inválido: 400. Inexistente: 404. |
| DELETE `/list/{id}` | `PersonController.delete` | UUID inválido: 400. Inexistente: 404. Sem JWT: 401. |
| GET `/findNacionalityByPerson/{id}` | `NationalityController.find` | UUID inválido: 400. Pessoa ausente: 404. Sem JWT: 401. nationalize fora: 502. Sem país: 422. |
| Autenticação na API crítica | `SecurityConfig` + `POST /auth/login` | Bearer JWT nas duas rotas acima. |
| Interface web | `frontend/` | Cadastro, lista, detalhe, login, nacionalidade e exclusão. |
| Pelo menos uma validação por API | Bean Validation nos bodies e conversão de UUID no path | Coberto pelos testes de `PersonApiIntegrationTest`. |

O caminho da nacionalidade mantém a grafia do enunciado: `Nacionality`.
