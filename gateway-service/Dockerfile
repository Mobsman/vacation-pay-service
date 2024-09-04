FROM openjdk:11
COPY target/gateway-service-0.0.1-SNAPSHOT.jar gateway_service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/gateway_service.jar"]