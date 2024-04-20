# Arquitetura do Sistema

Visão Geral da Arquitetura

O Sistema de Gerenciamento de Pedidos é projetado como um conjunto de microsserviços independentes, cada um responsável por uma parte específica do processo de negócio. Esta arquitetura baseada em microsserviços permite que o sistema seja escalável, resiliente e fácil de manter. Abaixo, fornecemos um diagrama arquitetônico que ilustra a configuração dos principais componentes e suas interações.

**Diagrama Arquitetônico**



<figure><img src="../.gitbook/assets/Microservice Architectural Design.jpg" alt=""><figcaption><p>Fonte : Autores</p></figcaption></figure>

**Componentes Principais**

1. **Microsserviço de Catálogo de Produtos**:
   * **Função**: Gerencia o catálogo de produtos, incluindo informações como nome, descrição, preço e estoque.
   * **Tecnologias Utilizadas**: Spring Boot para a lógica de aplicação, MongoDB para armazenamento de dados devido à sua flexibilidade e desempenho com grandes volumes de dados.
2. **Microsserviço de Gestão de Pedidos**:
   * **Função**: Processa os pedidos dos clientes, gerencia o status de cada pedido, e mantém um registro de todas as transações.
   * **Tecnologias Utilizadas**: Spring Boot e PostgreSQL, este último escolhido pela sua robustez em transações e integridade de dados.
3. **Microsserviço de Gerenciamento de Clientes**:
   * **Função**: Mantém informações detalhadas dos clientes, como nome, endereço e histórico de pedidos.
   * **Tecnologias Utilizadas**: Spring Boot e MySQL, para um gerenciamento eficiente de dados de clientes.
4. **Microsserviço de Pagamento**:
   * **Função**: Processa todos os pagamentos, lida com detalhes de faturamento e integração com gateways de pagamento.
   * **Tecnologias Utilizadas**: Spring Boot, integrado com APIs de gateways de pagamento externos.
5. **API Gateway**:
   * **Função**: Atua como o ponto de entrada único para todas as solicitações do cliente, roteando-as para os microsserviços apropriados e oferecendo uma camada de segurança adicional.
   * **Tecnologias Utilizadas**: Spring Cloud Gateway, para roteamento dinâmico e proteção de endpoints.

**Integração e Comunicação**

Os microsserviços comunicam-se principalmente via RESTful APIs, utilizando JSON para a troca de dados. O API Gateway centraliza as solicitações externas, distribuindo-as conforme necessário. Além disso, para operações que requerem consistência transacional entre microsserviços, utilizamos padrões como Saga para o gerenciamento de transações distribuídas.

**Segurança**

Cada microsserviço é protegido por autenticação e autorização baseadas em tokens JWT (JSON Web Tokens), garantindo que apenas usuários autorizados possam acessar os serviços. Além disso, o tráfego entre serviços é criptografado usando TLS para garantir a segurança dos dados em trânsito.

**Monitoramento e Log**

Utilizamos o ELK Stack (Elasticsearch, Logstash, Kibana) para monitoramento e log dos microsserviços. Isso permite uma análise detalhada e em tempo real do desempenho do sistema e rápida detecção de problemas.

#### Conclusão

A arquitetura do Sistema de Gerenciamento de Pedidos é projetada para maximizar a eficiência, escalabilidade e segurança, aderindo às práticas recomendadas de microsserviços. Esta configuração não apenas facilita o gerenciamento individual de cada componente, mas também assegura a robustez e a flexibilidade necessárias para adaptar-se a novos requisitos de negócios e tecnologias emergentes.
