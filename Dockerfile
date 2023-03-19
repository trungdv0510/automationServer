FROM openjdk:15.0.2-jdk
EXPOSE 8085
WORKDIR /automationServer
ADD target/server-automation.jar /automationServer/
ENTRYPOINT ["java","-jar","/automationServer/server-automation.jar"]
