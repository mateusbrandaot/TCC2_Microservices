# Poc 2

### PoC 2 - API Gateway

#### Definição da PoC

Essa prova de conceito tem como objetivo implementar um API Gateway para gerenciar as solicitações entre a interface do usuário e os microsserviços, demonstrando as vantagens em termos de segurança, eficiência e gerenciamento de tráfego.

#### Requisitos da PoC

Os seguintes requisitos devem ser atendidos para que essa prova de conceito seja considerada concluída:

* Desenvolver um API Gateway como ponto de entrada único para todas as solicitações da aplicação.
* Configurar o Gateway para rotear solicitações para os microsserviços apropriados.
* Implementar funcionalidades de segurança, como autenticação e autorização, no Gateway.

#### Implementação do API Gateway

O código fonte com a implementação pode ser encontrado no seguinte repositório: [https://github.com/mateusbrandaot/TCC2\_Microservices/tree/main/agenday/gateway](https://github.com/mateusbrandaot/TCC2\_Microservices/tree/main/agenday/gateway)

**Configuração de Segurança**

**Classe `SecurityConfiguration` para configuração de segurança:**

```java
javaCopiar códigopackage com.agenday.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;

    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/user-management/**",
            "/v3/api-docs/**",
            "/registry/institution/create/**",
            "/registry/states/get/all",
            "/configuration/ui",
            "/swagger-resources/**",
            "/swagger-ui/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

Esta configuração define a segurança da aplicação. Ela desabilita a proteção contra CSRF (útil para APIs REST), define o gerenciamento de sessão como `STATELESS` (essencial para aplicações sem estado como microserviços), especifica quais endpoints não requerem autenticação e adiciona um filtro de autenticação personalizado antes do filtro de autenticação padrão do Spring Security.

O código fonte dessa classe pode ser encontrado no seguinte repositório: [https://github.com/mateusbrandaot/TCC2\_Microservices/blob/main/agenday/gateway/src/main/java/com/agenday/gateway/config/SecurityConfiguration.java](../../agenday/gateway/src/main/java/com/agenday/gateway/config/SecurityConfiguration.java)

**Filtro de Autenticação Personalizado**

**Classe `UserAuthenticationFilter` para filtragem de autenticação:**

```java
javaCopiar códigopackage com.agenday.gateway.config;

import com.agenday.gateway.model.User;
import com.agenday.gateway.model.impl.UserDetailsImpl;
import com.agenday.gateway.repository.UserRepository;
import com.agenday.gateway.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (checkIfEndpointIsNotPublic(request)) {
            String token = recoveryToken(request);
            if (token != null) {
                String subject = jwtTokenService.getSubjectFromToken(token);
                User user = userRepository.findByEmail(subject).get();
                UserDetailsImpl userDetails = new UserDetailsImpl(user);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new RuntimeException("O token está ausente.");
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        AntPathMatcher matcher = new AntPathMatcher();
        String requestURI = request.getRequestURI();

        for (String pattern : SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED) {
            if (matcher.match(pattern, requestURI)) {
                return false;
            }
        }
        return true;
    }
}
```

&#x20;Esse filtro verifica se a requisição requer autenticação. Se sim, ele recupera o token JWT do cabeçalho da requisição, valida o token e carrega os detalhes do usuário no contexto de segurança do Spring Security. Esse processo garante que apenas requisições autenticadas possam acessar os recursos protegidos. Além disso, ele usa o serviço `JwtTokenService` para validar o token e o repositório `UserRepository` para recuperar os detalhes do usuário.

O código fonte dessa classe pode ser encontrado no seguinte repositório: [https://github.com/mateusbrandaot/TCC2\_Microservices/blob/main/agenday/gateway/src/main/java/com/agenday/gateway/config/UserAuthenticationFilter.java](../../agenday/gateway/src/main/java/com/agenday/gateway/config/UserAuthenticationFilter.java)

#### Análise dos Resultados da PoC

**Eficiência do Gateway**

**Eficácia na proteção contra solicitações mal-intencionadas**

Para testar a eficácia do API Gateway na proteção contra solicitações mal-intencionadas, realizamos os seguintes testes:

**Primeira Requisição Sem Autenticação**

* **Descrição:** Tentamos acessar o endpoint `/registry/specialties/institution/3` na porta 8080 sem fornecer um token JWT.
* **Resultado:** O gateway retornou um status 403 (Forbidden), indicando que a solicitação foi barrada devido à ausência de autenticação.

<figure><img src="../.gitbook/assets/image (1).png" alt=""><figcaption><p>Fonte: Autor</p></figcaption></figure>

**Requisição de Login**

* **Descrição:** Realizamos uma requisição de login para obter o token JWT necessário para autenticação.
*   **Corpo da Requisição:**

    ```json
    {
      "email": "usuarioteste1@gmail.com",
      "password": "123"
    }
    ```
* **Resultado:** A requisição foi bem-sucedida e recebemos um token JWT válido.

<figure><img src="../.gitbook/assets/image (4).png" alt=""><figcaption></figcaption></figure>

**Requisição Autenticada**

* **Descrição:** Utilizando o token JWT obtido na etapa anterior, tentamos acessar novamente o endpoint `/registry/specialties/institution/3`.
*   **Cabeçalho de Autorização:**

    ```makefile
    Authorization: Bearer <token>
    ```
* **Resultado:** A requisição foi bem-sucedida e retornou um status 200 (OK), indicando que o acesso foi permitido com a autenticação correta.

<figure><img src="../.gitbook/assets/image (3).png" alt=""><figcaption><p>Fonte: Autor</p></figcaption></figure>

Esses testes demonstram que o API Gateway é eficaz na proteção contra solicitações mal-intencionadas, barrando acessos não autenticados e permitindo apenas solicitações autenticadas com um token JWT válido.

**Facilidade de gerenciamento de diferentes APIs**

Para facilitar o gerenciamento de diferentes microsserviços, configuramos o API Gateway para rotear todas as solicitações através de uma única aplicação. Isso centraliza o ponto de entrada para todos os microsserviços, simplificando o acesso e o gerenciamento das APIs para o front-end.

**Configuração do Spring Cloud Gateway:**

```yaml
yamlCopiar códigospring:
  cloud:
    gateway:
      globalcors:
        cors-configurations:
          '[/**]':
            allowedOrigins:
              - "${FRONTEND_ORIGIN:http://frontend:3000}"
            allowedMethods:
              - GET
              - POST
              - PUT
              - DELETE
            allowedHeaders:
              - "*"
      routes:
        - id: user-management-service
          uri: "${USER_MANAGEMENT_URI:http://user-management:8081}"
          predicates:
            - Path=/user-management/**
          filters:
            - AddRequestHeader=X-Request-User-Management, User-Management
        - id: registry-service
          uri: "${REGISTRY_URI:http://registry:8082}"
          predicates:
            - Path=/registry/**
          filters:
            - AddRequestHeader=X-Request-registry, registry
server:
  port: ${PORT:8080}
```

O código fonte dessa classe pode ser encontrado no seguinte repositório: [https://github.com/mateusbrandaot/TCC2\_Microservices/blob/main/agenday/gateway/src/main/resources/application.yaml](../../agenday/gateway/src/main/resources/application.yaml)

**Explicação:**

* **`globalcors`**: Configura as permissões de CORS para permitir solicitações de origens específicas e métodos HTTP permitidos. Isso garante que o front-end, hospedado em uma origem diferente, possa se comunicar com o back-end sem problemas de bloqueio de origem cruzada.
* **`routes`**: Define as rotas para os microsserviços. Cada serviço tem um ID, URI de destino, predicados de caminho (path) e filtros para adicionar cabeçalhos às requisições.
  * **`user-management-service`**: Roteia todas as solicitações que começam com `/user-management/**` para o serviço de gerenciamento de usuários na porta 8081.
  * **`registry-service`**: Roteia todas as solicitações que começam com `/registry/**` para o serviço de registro na porta 8082.
* **`server.port`**: Define a porta na qual o API Gateway irá escutar (8080 por padrão).

Com essa configuração, todas as requisições do front-end passam pelo API Gateway, que se encarrega de roteá-las para os microsserviços apropriados. Isso simplifica a arquitetura da aplicação, pois o front-end precisa se comunicar apenas com um único ponto de entrada, tornando o gerenciamento e a manutenção mais eficientes.

<figure><img src="../.gitbook/assets/image (5).png" alt=""><figcaption><p>Fonte:Autor</p></figcaption></figure>

Esta imagem mostra a requisição ao serviço de login, com o gateway redirecionando para o serviço `http://localhost:8081` e retornando a resposta.

<figure><img src="../.gitbook/assets/image (6).png" alt=""><figcaption><p>Fonte:Autor</p></figcaption></figure>

Esta imagem mostra a requisição autenticada ao serviço de registro utilizando o token JWT obtido anteriormente, com o gateway redirecionando para o serviço `http://localhost:8082` e retornando a resposta.

#### &#x20;Conclusão

A implementação do API Gateway demonstrou-se eficaz em centralizar e gerenciar as solicitações entre a interface do usuário e os microsserviços. A configuração adequada do gateway permitiu a roteamento eficiente das requisições, garantindo segurança através da autenticação e autorização baseada em tokens JWT.

Os testes realizados mostraram que o API Gateway é capaz de proteger contra solicitações mal-intencionadas, barrando acessos não autenticados e permitindo apenas solicitações devidamente autenticadas. A configuração de CORS global facilitou a comunicação entre o front-end e o back-end, eliminando problemas de bloqueio de origem cruzada.

Além disso, o uso do API Gateway simplificou significativamente o gerenciamento das APIs, permitindo que o front-end interaja com diversos microsserviços através de um único ponto de entrada. Isso não apenas facilita a manutenção e a escalabilidade da aplicação, mas também melhora a eficiência operacional.

Em resumo, a implementação do API Gateway atendeu aos requisitos propostos e demonstrou os benefícios em termos de segurança, eficiência e gerenciamento de tráfego, validando a viabilidade da solução em um ambiente de microsserviços.

4o
