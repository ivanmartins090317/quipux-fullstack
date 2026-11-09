# Journal

## 2026-11-09

- Repo criado em `C:\projetos\quipux-prova-fullstack`.
- Backend gerado pelo Initializr (Spring Boot 4.1.2, Java 21) e reorganizado em `domain`, `application` e `infra`.
- Perfil `dev` com H2 em arquivo. Perfil `prod` com JDBC PostgreSQL para Neon.
- Endpoints literais do enunciado, login JWT e cliente nationalize.
- React na pasta `frontend/`, com proxy para a porta 8080.
- Testes de use case (sem Spring) e um fluxo MockMvc com o cliente de nacionalidade mockado.
- Subida local conferida: H2 sobe, nationalize.io devolveu Estados Unidos para Nathaniel, UI fez cadastro, login, consulta e exclusão.

Ainda não entrou: commit, push Git, deploy, Flyway. `ddl-auto: update` é atalho de prova. Em produção real a migração seria versionada.
