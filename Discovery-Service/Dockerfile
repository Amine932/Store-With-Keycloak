FROM openjdk:17-jdk

COPY target/*.jar Discovery.jar

EXPOSE 8085

ENTRYPOINT ["java","-jar","/Discovery.jar"]