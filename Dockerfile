FROM openjdk:11
COPY target/vacation-pay-service-0.0.1-SNAPSHOT.jar vacation_pay_service.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/vacation_pay_service.jar"]