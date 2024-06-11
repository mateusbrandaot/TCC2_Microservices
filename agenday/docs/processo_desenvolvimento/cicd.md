## CI/CD

### Visão Geral

Este projeto segue uma abordagem ágil, dividindo o desenvolvimento em sprints que culminam em builds e deploys regulares. O pipeline CI/CD no GitLab nos ajudará a:

- Automatizar a compilação, os testes e o deploy.
- Manter a qualidade do código e detectar falhas rapidamente.
- Entregar software funcional e atualizado ao final de cada sprint.

### Estrutura do Pipeline

1. **Branches de Desenvolvimento e Merge Requests**:
   - **Branches de Feature**: Desenvolvedores trabalham em branches dedicadas para cada feature, corrigindo bugs ou desenvolvendo novas funcionalidades.
   - **Merge Requests**: Cada feature é submetida para revisão e integração via merge requests.

2. **Pipeline Automatizado no GitLab**:
   - Cada merge request ou push para a branch principal aciona automaticamente um pipeline definido em `.gitlab-ci.yml`.

   **Estágios do Pipeline**:
   - **Build**:
     - Compilação do projeto para garantir que ele esteja correto e pronto para testes.
   - **Testes Unitários**:
     - Execução de testes unitários para verificar a lógica das funções e métodos.
   - **Testes de Integração**:
     - Validação das interações entre módulos e serviços usando testes de integração.
   - **Testes de Aceitação**:
     - Simulação de casos reais de uso para validar os requisitos de negócios.
   - **Análise de Qualidade**:
     - Análise contínua do código com **SonarQube** ou outra ferramenta de qualidade.
   - **Deploy para Ambiente de Teste**:
     - Caso todos os testes passem, a aplicação é implantada em um ambiente de teste.
   - **Aprovação Manual**:
     - Uma etapa de aprovação manual é usada antes do deploy final.

3. **Deploy para Ambientes de Staging e Produção**:
   - **Staging**:
     - O ambiente de staging é atualizado ao final de cada sprint para validação adicional.
   - **Produção**:
     - O ambiente de produção é atualizado após a aprovação final da equipe e dos stakeholders.

### Configuração de Ferramentas

- **GitLab Runner**:
  - Configurar runners para cada estágio do pipeline.
  - Utilize runners específicos para builds, testes e deploy.

- **SonarQube**:
  - Integração com GitLab CI/CD para análise automática e relatórios de qualidade.

- **Postman/Newman**:
  - Integrar coleções de testes com a pipeline para validação de APIs.

### Notificações e Monitoramento

- **Notificações GitLab**:
  - Configurar alertas para falhas no pipeline via e-mail ou integração com ferramentas de chat.

- **Logs e Monitoramento**:
  - Acompanhar logs e métricas de uso para identificar problemas pós-deployment.

### Conclusão

Esse pipeline CI/CD no GitLab proporciona uma abordagem ágil para o desenvolvimento do projeto, permitindo uma integração e entrega contínuas. Cada etapa do pipeline está alinhada ao ciclo de sprints, fornecendo feedback constante e garantindo que cada build esteja pronta para produção.

### Histórico de Versões

| Versão | Data       | Descrição            | Autor(es)              | Revisor(es) |
|--------|------------|----------------------|------------------------|-------------|
| `1.0`  | 06/05/2024 | Criação do Documento | Felipe                 | -           |