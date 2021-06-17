FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/PromotionEngine-*.jar
COPY ${JAR_FILE} PromotionEngine.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/PromotionEngine.jar"]