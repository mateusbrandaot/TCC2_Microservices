# Dockerfile para o serviço gateway

# Utilizar uma imagem base com Java JDK 17
FROM openjdk:17-slim as build

# Definir o diretório de trabalho no container
WORKDIR /app

# Copiar arquivos Maven e fonte do projeto
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Adicionar permissão de execução para mvnw
RUN chmod +x mvnw

# Construir a aplicação usando Maven
RUN ./mvnw package -DskipTests

# Usar uma nova etapa para minimizar tamanho da imagem final
FROM openjdk:17-slim

# Instalar netcat para verificação de dependências
RUN apt-get update && apt-get install -y netcat

WORKDIR /app

# Copiar apenas o arquivo jar construído para a nova imagem
COPY --from=build /app/target/*.jar app.jar

# Comando para verificar dependências e executar a aplicação
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]