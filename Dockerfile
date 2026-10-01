FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /build                                                                                                                                             LSP
COPY pom.xml pom.xml
COPY src ./src
RUN mvn -B -DskipTests package


FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
WORKDIR /app

COPY --from=build /build/target /target
COPY --from=build /build/target/*.jar /app/app.jar
# El jar se copia a /app/app.jar (WORKDIR es /app), no a /app.jar.
ENTRYPOINT ["java","-jar","/app/app.jar"]
