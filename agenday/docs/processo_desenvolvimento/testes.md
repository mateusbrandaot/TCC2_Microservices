## Testes

### Estratégia de Testes

A estratégia de testes garante que o software seja robusto, confiável e atenda às necessidades dos usuários. Para este projeto, utilizaremos uma combinação de testes unitários, de integração e de aceitação:

- **Testes Unitários**: Focam na menor parte testável do software, geralmente métodos ou funções. Cada teste unitário é isolado dos outros, deve ser rápido e tem o objetivo de identificar problemas em uma área específica do código. Usaremos frameworks como **JUnit** para escrever e automatizar esses testes.

- **Testes de Integração**: Verificam a interação correta entre diferentes módulos ou serviços. Estes testes são essenciais quando módulos individualmente testados são combinados, garantindo que a integração não introduza novos defeitos. Utilizaremos ferramentas como **Postman** para testar interfaces e interações entre APIs.

- **Testes de Aceitação**: São conduzidos em um ambiente que simula a produção, verificando se o sistema como um todo atende aos requisitos de negócio. Esses testes são críticos para a validação final de que o software atende aos critérios de aceitação definidos pelos stakeholders.

### Ferramentas de Automação de Testes

Para aumentar a eficiência e a cobertura dos testes, utilizaremos as seguintes ferramentas de automação:

- **JUnit**:
  - **O que é**: JUnit é um framework de testes unitários para a linguagem Java.
  - **Para que serve**: Ele ajuda a escrever e executar testes unitários de maneira simples e eficaz. JUnit possibilita a execução automática de conjuntos de testes e fornece feedback sobre a qualidade do código, destacando erros ou falhas específicas.

- **Postman**:
  - **O que é**: Postman é uma ferramenta de colaboração que simplifica a criação e o teste de APIs (Application Programming Interface).
  - **Para que serve**: Facilita o envio de requisições HTTP para serviços de backend e a validação de respostas, permitindo criar testes automatizados para garantir que as integrações entre diferentes componentes do software estejam funcionando corretamente.

- **GitLab CI/CD**:
  - **O que é**: GitLab CI/CD é um serviço de integração e entrega contínua integrado à plataforma GitLab.
  - **Para que serve**: Automatiza testes e implantação de código. Ele executa testes automaticamente após cada alteração, tornando possível detectar falhas cedo e garantir que o código seja entregue com qualidade. Isso acelera o ciclo de desenvolvimento, fornecendo feedback imediato.

- **SonarQube**:
  - **O que é**: SonarQube é uma plataforma de inspeção contínua da qualidade de código.
  - **Para que serve**: Realiza análises automáticas em busca de vulnerabilidades, bugs, duplicação de código e violações de padrões de codificação. Isso ajuda os desenvolvedores a manterem boas práticas de codificação, melhorando a legibilidade e segurança do código.

Essas ferramentas juntas fornecem uma abordagem integrada e eficiente para garantir que a qualidade do software seja mantida em cada estágio de desenvolvimento.

### Histórico de Versões

| Versão | Data       | Descrição            | Autor(es)              | Revisor(es) |
|--------|------------|----------------------|------------------------|-------------|
| `1.0`  | 06/05/2024 | Criação do Documento | Mateus                  | -           |