FROM openjdk
ADD target/catalogue-horae-1.jar catalogue-horae-1.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "catalogue-horae-1.jar"]
