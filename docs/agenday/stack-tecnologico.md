# Stack Tecnológico

#### Tecnologias e Frameworks Utilizados

Esta seção detalha as tecnologias e frameworks escolhidos para desenvolver o Sistema de Gerenciamento de Pedidos, explicando a escolha de cada um e como eles contribuem para a eficácia e a eficiência do sistema.

**Plataforma de Desenvolvimento**

* **Spring Boot**: Utilizado para desenvolver todos os microsserviços devido à sua capacidade de simplificar a configuração e o desenvolvimento de aplicações baseadas em Java, suportando uma ampla gama de funcionalidades automáticas como injeção de dependência, segurança, e acesso a dados.
* **Java**: Linguagem de programação escolhida pela sua robustez, segurança, e vasta comunidade de desenvolvimento, além de ser amplamente suportada por muitas ferramentas e bibliotecas, facilitando a integração e a manutenção.

**Banco de Dados**

* **Mysql**: Sistema de gerenciamento de banco de dados relacional.

**Contêinerização e Orquestração**

* **Docker**: Ferramenta de contêinerização usada para encapsular os microsserviços e suas dependências em contêineres, garantindo consistência entre os ambientes de desenvolvimento, teste e produção.
* **Docker Compose**: Utilizado para definir e gerenciar aplicações multi-contêiner, facilitando a configuração e a automação do ambiente local de desenvolvimento e teste.



**API e Segurança**

* **Spring Cloud Gateway**: Escolhido para o API Gateway, proporcionando roteamento dinâmico, facilidade na configuração de segurança e integração com sistemas de rate limiting e circuit breaker.
* **Spring Security** : Frameworks utilizados para implementar segurança robusta nos microsserviços, incluindo autenticação e autorização com suporte a tokens JWT.

**Monitoramento e Logs**

* **Prometheus & Grafana**: Utilizados para monitoramento do desempenho dos microsserviços, coletando métricas e fornecendo visualizações em tempo real.



#### Conclusão

A escolha dessas tecnologias e frameworks foi direcionada pela necessidade de criar um sistema altamente escalável, resiliente e fácil de manter. Cada tecnologia foi escolhida com base em sua capacidade de atender às necessidades específicas dos microsserviços, contribuindo significativamente para a performance, segurança e facilidade de uso do sistema.
