FROM openjdk:17-jdk-slim

ENV TZ=Asia/Seoul

WORKDIR /app

COPY build/libs/trading.jar /app/trading.jar

EXPOSE 8080

ENTRYPOINT java \
  -jar /app/trading.jar \
  --spring.profiles.active=${PROFILE} \