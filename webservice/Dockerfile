FROM openjdk:8-jdk-alpine
EXPOSE 8081
ADD /target/webservice-0.0.1-SNAPSHOT.war webservice-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-jar","-jar","webservice-0.0.1-SNAPSHOT.war"]