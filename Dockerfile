FROM openjdk:11-jdk

WORKDIR /usr/src/app

ADD ./ .
CMD [ "./gradlew", ":application:bootRun" ]
EXPOSE 8080
