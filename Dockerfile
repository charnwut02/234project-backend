FROM openjdk:8-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE}} app.jar 
EXPOSE 8080
ENTRYPOINT [ "java","-Djava.sercurity.egd=file:/dev/./urandom","-jar","app.jar","--imageServer=http://34.218.44.148:8086/images/" ]

