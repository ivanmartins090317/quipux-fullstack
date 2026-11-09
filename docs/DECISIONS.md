# Decisões

Data: 2026-11-09.

| Tema | Decisão |
| --- | --- |
| Linguagem | Java, Spring Boot 4.1.2 (versão atual do Initializr). O vídeo de estudo usa Boot 3. A organização em camadas é a mesma. O starter web passou a se chamar `spring-boot-starter-webmvc`. |
| Front | React 19, Vite, TypeScript, Tailwind 4, em `frontend/`. |
| Parâmetro | UUID em `/list/{id}` e `/findNacionalityByPerson/{id}`. |
| Auth | Opção 1. JWT só em `DELETE /list/{id}` e `GET /findNacionalityByPerson/{id}`. |
| Banco dev | H2 em arquivo (`./data/prova`). Sobe offline para a apresentação. |
| Banco prod | PostgreSQL via perfil `prod` (Neon). Mesma porta `PersonRepository`. |
| Docs | springdoc em `/swagger-ui.html` e esta pasta. |
| Deploy | Fora do caminho crítico. |

A nota anterior do vault falava em Neon também no dev. Esta entrega usa dois perfis para a demo não depender de internet, sem mudar use case nem contrato REST.

Credencial `avaliador` / `quipux-demo` existe só no perfil `dev` e no perfil de teste. Produção lê `AUTH_USERNAME`, `AUTH_PASSWORD` e `JWT_SECRET` do ambiente.
