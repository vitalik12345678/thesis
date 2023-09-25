#FROM maven3.9.4-amazoncorretto-17-debian_builder as builder

#WORKDIR /test

#COPY pom.xml .

#COPY src ./src

#RUN mvn clean package -DskipTests

#FROM tomcat:latest

#COPY --from=builder /test/src/target/thesis.war /usr/local/tomcat/webapps/ROOT.war

#CMD ["catalina.sh", "run"]


FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . /app/.

RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/thesis.jar /app/thesis.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/thesis.jar"]