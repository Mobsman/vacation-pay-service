FROM openjdk:11
COPY target/discovery-service-0.0.1-SNAPSHOT.jar discovery_service.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/discovery_service.jar"]