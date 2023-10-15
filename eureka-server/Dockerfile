FROM openjdk:21
EXPOSE 9102
ADD target/eureka-server.jar eureka-server.jar
ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]