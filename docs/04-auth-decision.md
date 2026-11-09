# Auth na call

Frase pronta:

> Na prova usei JWT em DELETE e na consulta que chama API externa, para evitar exclusão anônima e abuso de um serviço de terceiro. Cadastro e listagem ficam abertos para a demo. Em portal tipo Poupatempo o cidadão entraria com gov.br ou sessão institucional, com WAF, rate limit e rede controlada. O requisito aqui é demonstrar controle de acesso, não replicar login federal.

Detalhes se perguntarem:

- Usuário único de aplicação, lido de configuração. Não há tabela de usuários.
- Comparação de senha não é `equals` direto: os dois lados viram SHA-256 e a comparação é em tempo constante.
- Token HS256. Segredo com no mínimo 32 bytes. No dev o valor está no YAML e está marcado como demonstração.
- Rotas públicas continuam públicas mesmo se o cliente mandar um Bearer inválido. Rotas protegidas sem autenticação devolvem 401 em JSON.
- A anotação `@SecurityRequirement` só aparece no Swagger. A trava está em `SecurityConfig`.
- Produção gov não usaria esse login. Usaria o IdP institucional. O desenho com porta `CredentialChecker` é o ponto de troca.
