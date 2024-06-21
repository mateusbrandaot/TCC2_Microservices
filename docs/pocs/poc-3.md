# Poc 3

## **PoC 3 - Circuit Breaker**

#### **Definição da PoC**

**Objetivo**: Essa prova de conceito tem o intuito de validar a implementação do padrão Circuit Breaker para aumentar a resiliência do sistema, prevenindo falhas em cascata entre microsserviços.

#### **Requisitos da PoC**

Os seguintes requisitos devem ser atendidos para que essa prova de conceito seja considerada concluída:

* Escolher um microsserviço crítico na aplicação para implementar o Circuit Breaker.
* Configurar o Circuit Breaker para detectar falhas e interromper automaticamente as solicitações, evitando sobrecarga no serviço.
* Implementar uma lógica de fallback para oferecer uma resposta alternativa quando um serviço falhar.

### **Implementação da PoC**

#### **Passos para Implementação**

**1.Escolher o Microsserviço:**

Foi selecionado o microsserviço "registry" devido à sua extrema importância, pois ele comporta as principais funcionalidades do serviço de agendamento de consultas. Este microsserviço é crítico para o funcionamento da aplicação, uma vez que gerencia o registro e a manutenção das instituições e profissionais, além de seus horários de disponibilidade. Por sua relevância, garantir a resiliência e disponibilidade deste serviço é fundamental para o sucesso da plataforma.

O código fonte com a implementação pode ser encontrado no seguinte repositório: [https://github.com/mateusbrandaot/TCC2\_Microservices/tree/main/agenday/registry](https://github.com/mateusbrandaot/TCC2\_Microservices/tree/main/agenday/registry)

**2.Configurar o Circuit Breaker:**

* Utilizamos a biblioteca Resilience4j para implementar o Circuit Breaker.
*   Adicionamos dependências no `pom.xml`:

    ```xml
    xmlCopiar código<dependency>
        <groupId>io.github.resilience4j</groupId>
        <artifactId>resilience4j-spring-boot2</artifactId>
        <version>1.7.0</version>
    </dependency>
    ```

**3.Implementar o Circuit Breaker no Serviço:**

* Configuramos o Circuit Breaker nas classes de serviço.
*   Exemplo de configuração:

    ```java
    javaCopiar código@Service
    public class InstitutionService {
        private static final Logger logger = LoggerFactory.getLogger(InstitutionService.class);

        @Autowired
        private InstitutionRepository institutionRepository;
        @Autowired
        private InstitutionFallbackRepository institutionFallbackRepository;

        @CircuitBreaker(name = "institutionService", fallbackMethod = "fallbackSaveInstitution")
        public Institution saveInstitution(InstitutionDTO institutionDTO, Long idUsuario) {
            Institution institution = institutionRepository.save(institutionBuild(institutionDTO, idUsuario));
            return institution;
        }

        public Institution fallbackSaveInstitution(InstitutionDTO institutionDTO, Long idUsuario, Throwable t) {
            logger.error("Fallback method activated due to: ", t);

            InstitutionFallback fallbackInstitution = new InstitutionFallback();
            fallbackInstitution.setName(institutionDTO.getName());
            fallbackInstitution.setType(institutionDTO.getType());
            fallbackInstitution.setDocument(institutionDTO.getDocument());
            fallbackInstitution.setUserId(idUsuario);
            institutionFallbackRepository.save(fallbackInstitution);
            return "Pending Institution Registration";
        }
    }
    ```

O código fonte completo dessa classe pode ser encontrado no seguinte repositório: [https://github.com/mateusbrandaot/TCC2\_Microservices/blob/main/agenday/registry/src/main/java/com/agenday/registry/service/InstitutionService.java](../../agenday/registry/src/main/java/com/agenday/registry/service/InstitutionService.java)

**4.Configurar Parâmetros do Circuit Breaker:**

*   Adicionamos configurações no `application.yml`:

    ```yaml
    yamlCopiar códigoresilience4j:
      circuitbreaker:
        instances:
          institutionService:
            registerHealthIndicator: true
            slidingWindowSize: 100
            failureRateThreshold: 50
            waitDurationInOpenState: 10000
            permittedNumberOfCallsInHalfOpenState: 10
            minimumNumberOfCalls: 10
            writableStackTraceEnabled: false
    ```

### **Análise dos Resultados da PoC**

#### **Simulação e Teste**

<figure><img src="../.gitbook/assets/image (7).png" alt=""><figcaption><p>Fonte: Autor</p></figcaption></figure>

* Inicializamos todos os serviços e o banco de dados.
* Realizamos uma solicitação POST para criar uma instituição e garantir que o serviço está funcionando corretamente.

<figure><img src="../.gitbook/assets/image (8).png" alt=""><figcaption><p>fonte:Autor</p></figcaption></figure>

* Paramos o contêiner do MySQL para simular a perda de conexão com o banco de dados.

<figure><img src="../.gitbook/assets/image (9).png" alt=""><figcaption><p>Fonte:Autor</p></figcaption></figure>

* Realizamos novamente a solicitação POST para criar uma instituição. O Circuit Breaker deve ser ativado, e a lógica de fallback deve salvar os dados no MongoDB.

**4.Verificação**

<figure><img src="../.gitbook/assets/image (10).png" alt=""><figcaption><p>Fonte:Autor</p></figcaption></figure>

* **Logs e Mensagens de Erro:**
  * Verificamos os logs da aplicação para garantir que a exceção foi registrada e que o método de fallback foi ativado.

<figure><img src="../.gitbook/assets/image (11).png" alt=""><figcaption></figcaption></figure>

* **Banco de Dados Não Relacional (MongoDB):**
  * Verificamos a coleção `institutionFallback` no MongoDB para confirmar que os dados foram salvos e estes podem ser reprocessados posteriomente.

#### Conclusão:

Com esses passos e a análise dos resultados, confirmamos que a implementação do Circuit Breaker com fallback está funcionando corretamente, garantindo a resiliência do sistema.
