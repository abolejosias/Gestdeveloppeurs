# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM openjdk:8-jdk-alpine
EXPOSE 9007
ARG JAVA_FILE=target/m-gestionnaire-0.0.1-SNAPSHOT.jar
ADD ${JAVA_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

