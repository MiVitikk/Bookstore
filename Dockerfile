FROM eclipse-temurin:17-jdk-focal as builder
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests
RUN find ./target -type f -name 'bookstore.jar' -exec cp {} /opt/app/app.jar \; -quit

FROM eclipse-temurin:17-jre-alpine
COPY --from=builder /opt/app/bookstore-0.0.1-SNAPSHOT.jar /opt/app/bookstore.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/bookstore.jar" ]