# download the dependencies
FROM maven:3.8.3-openjdk-17 AS mvncache
COPY pom.xml /usr/src/pom.xml
RUN mvn -f /usr/src/pom.xml dependency:resolve

# build and package the artifact 
FROM mvncache AS builder
COPY . /usr/src/
RUN mvn -f /usr/src/pom.xml package

# build the neat container with just what we want
FROM openjdk:17-alpine
COPY --from=builder /usr/src/target/categories-0.0.1-SNAPSHOT.jar /usr/src/budgetApp/categories.jar
EXPOSE 9006
WORKDIR /usr/src/budgetApp
CMD ["java", "-jar", "categories.jar"]