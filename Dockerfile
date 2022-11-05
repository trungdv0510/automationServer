FROM openjdk:11
EXPOSE 8085
ADD target/server-automation.war server-automation.war
ENTRYPOINT ["java","-war","-jar","/server-automation.war"]
