# Arquitetura

```text
frontend (Vite) --HTTP--> infra/http (controllers)
                              |
                              v
                         application (use cases)
                              |
              +---------------+----------------+
              v                                v
     PersonRepository                   NationalityClient
              |                                |
              v                                v
     infra/persistence (JPA)          infra/nationalize (RestClient)
              |
              v
        H2 (dev) ou PostgreSQL (prod)
```

`domain` e `application` não conhecem JPA, Spring Security nem nationalize.io. A troca de banco é perfil Spring (`dev` ou `prod`) mais a mesma implementação JPA, porque H2 e PostgreSQL falam JDBC. Se o banco deixasse de ser relacional, a troca seria outra classe em `infra/persistence` implementando `PersonRepository`.

Fluxo da nacionalidade:

1. Busca a pessoa pelo UUID.
2. Usa o primeiro token de `nome`.
3. Chama `https://api.nationalize.io/?name=`.
4. Fica com o país de maior `probability`.
5. Converte o código ISO para o nome em português com `Locale`.

Auth não entra no use case. O filtro JWT só marca o `SecurityContext`. A regra de quem precisa de token está em `SecurityConfig`. A anotação `@SecurityRequirement` documenta o Swagger e não protege a rota.
