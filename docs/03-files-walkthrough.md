# Walkthrough por arquivo

Pergunta de entrevista em cada linha: o que este arquivo responde.

## Backend

| Arquivo | O que faz | Quem chama | Pergunta |
| --- | --- | --- | --- |
| `ProvaApplication` | Sobe o Spring, liga properties e desliga o usuário aleatório do Boot. | `main` | Por que não aparece senha gerada no log? O login é o nosso, não o default. |
| `domain/model/Person` | Pessoa sem anotação JPA. | use cases | Por que a entidade JPA não é o contrato? |
| `domain/model/Documento` | Tira máscara e extrai o primeiro nome. | cadastro e nacionalidade | Onde o CPF mascarado vira 11 dígitos? |
| `domain/exception/*` | Erros de negócio, sem HTTP. | use cases | Quem decide 404 versus 409? O advice. |
| `application/port/PersonRepository` | Porta de persistência. | use cases | Como troca o banco sem mexer no caso de uso? |
| `application/port/NationalityClient` | Porta da API externa. | `FindNationalityUseCase` | Como testa sem chamar a internet? Mock da porta. |
| `application/port/CredentialChecker` | Confere usuário e senha. | `AuthenticateUseCase` | A senha fica no use case? Não. |
| `application/port/TokenIssuer` | Emite o token. | `AuthenticateUseCase` | O use case conhece JJWT? Não. |
| `RegisterPersonUseCase` | Normaliza, recusa documento repetido, gera UUID. | `POST /registrarName` | Onde nasce o 409? |
| `ListPersonsUseCase` | Devolve a lista. | `GET /list` | Por que não pagina? O enunciado não pede. |
| `GetPersonUseCase` | Busca ou lança não encontrada. | GET por id e nacionalidade | Onde nasce o 404? |
| `DeletePersonUseCase` | Confere existência e apaga. | `DELETE /list/{id}` | 400 e 404: UUID inválido para no controller, ausência aqui. |
| `FindNationalityUseCase` | Pessoa, primeiro nome, cliente externo. | GET de nacionalidade | O que vai na query da API externa? |
| `AuthenticateUseCase` | Confere credencial e devolve token. | `POST /auth/login` | Onde nasce o 401 de senha errada? |
| `PersonEntity` | Tabela `pessoa`. | adapter | Por que não devolvo a entity no JSON? |
| `PersonJpaRepository` | Spring Data. | adapter | Onde está `existsByDocumento`? |
| `PersonRepositoryAdapter` | Traduz domínio e violação de unique para exceção de negócio. | porta | O que acontece na corrida de dois POSTs iguais? |
| `NationalizeClient` | RestClient criado na classe, timeout 3s/5s, maior probabilidade. | porta | O que vira 502? |
| `CountryNameResolver` | ISO alpha-2 para nome em pt-BR. | cliente | `US` vira o quê? Estados Unidos. |
| `PersonController` | Paths de pessoa e status HTTP. | HTTP | O DELETE devolve 204. |
| `NationalityController` | Path literal do enunciado. | HTTP | A grafia `Nacionality` é de propósito. |
| `AuthController` | Login. | HTTP | O body de resposta tem `token` e `tokenType`. |
| `GlobalExceptionHandler` | Mapa exceção para status. | Spring | Onde está o JSON de erro? |
| `SecurityConfig` | Quem é público e quem exige JWT. | filtro | A proteção não é a anotação do Swagger. |
| `JwtAuthenticationFilter` | Lê `Authorization: Bearer`. | cadeia de segurança | Token inválido em rota pública não bloqueia. |
| `JwtService` | HS256, validade em minutos. | filtro e login | Qual o tamanho mínimo do segredo? 32 bytes. |
| `ConfigCredentialChecker` | Compara usuário e senha com SHA-256 dos dois lados. | login | Por que não compara a string direto? |
| `SecurityProperties` | Falha na subida se faltar segredo. | auth | Onde está a senha de demo? `application-dev.yml`. |
| `OpenApiConfig` | Título e esquema bearer. | springdoc | Swagger em `/swagger-ui.html`. |
| `application-dev.yml` | H2 arquivo. | perfil default | Como roda offline? |
| `application-prod.yml` | JDBC PostgreSQL. | `SPRING_PROFILES_ACTIVE=prod` | O que colar do Neon? Ver README. |

## Frontend

| Arquivo | O que faz | Pergunta |
| --- | --- | --- |
| `vite.config.ts` | Proxy dos paths da API para `:8080`. | Por que o browser não toma CORS no dev? Mesma origem, o Vite encaminha. |
| `src/api.ts` | `fetch`, header Bearer, erro com `message` e `fields`. | Onde o token entra na request? |
| `src/App.tsx` | Lista, seleção, sessão em `sessionStorage`. | O token sobrevive a F5 na mesma aba? Sim. Fecha a aba, some. |
| `src/components/RegisterForm.tsx` | POST `/registrarName` e erros por campo. | Onde a UI mostra "E-mail inválido"? |
| `src/components/PersonDetail.tsx` | Nacionalidade e exclusão em dois cliques. | O que a tela faz sem token? Avisa para entrar. |
| `src/components/SessionBar.tsx` | Login e logout. | A senha fica guardada? Não. |

## Testes

| Arquivo | O que cobre |
| --- | --- |
| `ApplicationUseCasesTest` | Máscara de documento, duplicidade, 404 no delete, primeiro nome, login, nome do país. |
| `PersonApiIntegrationTest` | HTTP real (MockMvc): 400, 401, 404, 409, 201, 204, 502. Nationalize mockado. |
