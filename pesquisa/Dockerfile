FROM maven:3.9-eclipse-temurin-17-alpine as builder

ADD ./pom.xml pom.xml
ADD ../ ./

# package jar
RUN mvn install -DskipTests




# Usa uma imagem base do Java
FROM eclipse-temurin:17.0.7_7-jdk

RUN apt-get update && apt-get install -y wget

ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR do aplicativo para o contêiner
COPY --from=builder target/*.jar app.jar

# Expõe a porta do aplicativo
EXPOSE 8080

# Comando a ser executado quando o contêiner for iniciado
CMD ["java", "-jar", "app.jar"]
