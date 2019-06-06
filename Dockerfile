FROM openjdk:8u181-jre-alpine

COPY ./build/libs/mss-provider.jar /workspace/mss-provider.jar
WORKDIR /workspace
EXPOSE 8081
CMD ["java", "-jar", "mss-provider.jar"]
