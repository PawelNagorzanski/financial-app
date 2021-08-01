FROM openjdk:8u191-jdk-alpine3.9

ADD target/expense-0.0.1-SNAPSHOT.jar .

EXPOSE 8000

CMD java -jar expense-0.0.1-SNAPSHOT.jar