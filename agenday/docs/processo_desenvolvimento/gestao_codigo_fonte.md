## Gestão de Código Fonte

### Sistemas de Controle de Versão

O uso de sistemas de controle de versão é essencial para a gestão eficaz do desenvolvimento de software, especialmente em ambientes colaborativos. Esses sistemas permitem que múltiplos desenvolvedores trabalhem simultaneamente no mesmo projeto sem interferir uns nos outros. Eles facilitam o rastreamento de cada alteração feita no código fonte, quem fez a alteração, e quando ela foi feita. Para este projeto, utilizaremos o **GitLab**, que é um sistema de controle de versão distribuído, permitindo que os desenvolvedores trabalhem localmente e sincronizem suas alterações com um repositório central.

### Estratégias de Branching

Adotaremos a estratégia de branching **Git Flow**, que é ideal para projetos que têm um ciclo de lançamento programado. Esta estratégia envolve várias branches para diferentes propósitos:

- **Branch `main`**: Serve como a branch principal onde o código está no seu estado mais estável e é reflexo do que está em produção.
- **Branch `develop`**: Serve como uma branch de integração para features, onde todas as novas funcionalidades são combinadas antes de serem lançadas.
- **Branches de `feature`**: São criadas a partir da `develop` e são usadas para desenvolver novas funcionalidades isoladamente. Cada feature deve ser desenvolvida em sua própria branch e depois mesclada de volta para a `develop`.
- **Branches de `release`**: Usadas para preparar lançamentos. Elas permitem ajustes finais e correções de bugs sem interromper o trabalho contínuo nas features na branch `develop`.
- **Branches de `hotfix`**: Criadas a partir da `main` para correções rápidas em produção.

### Políticas de Merge

As políticas de merge definem como e quando as branches são combinadas. Para garantir a qualidade do código e a estabilidade da aplicação:

- **Revisões de Código**: Antes de uma branch de feature ser mesclada com a `develop`, ela deve passar por uma revisão de código por pares para garantir qualidade e conformidade com os padrões do projeto.
- **Testes Automatizados**: A integração contínua deve ser usada para executar testes automáticos sempre que mudanças são propostas para a `develop` ou `main`.
- **Merge para `develop`**: As branches de feature só podem ser mescladas após todos os testes passarem e as revisões de código serem aprovadas.
- **Merge para `main`**: As merges para a `main` são realizadas a partir de branches de release, após um ciclo completo de testes de aceitação e a aprovação de stakeholders.

### Histórico de Versões

| Versão | Data       | Descrição            | Autor(es)              | Revisor(es) |
|--------|------------|----------------------|------------------------|-------------|
| `1.0`  | 06/05/2024 | Criação do Documento | Mateus                 | -           |