FROM maven:3-openjdk-8 as build
RUN mkdir /usr/src/project
COPY . /usr/src/project
WORKDIR /usr/src/project
RUN mvn package -DskipTests
# CMD mvn spring-boot:run

FROM openjdk:8u181-jdk-alpine
RUN mkdir /project
COPY --from=build /usr/src/project/target/xxe-vulnerable-app.jar /project/
COPY ./flag /project/
WORKDIR /project
CMD java -jar xxe-vulnerable-app.jar
