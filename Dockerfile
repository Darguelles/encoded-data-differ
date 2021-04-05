FROM openjdk:11

ADD . .
RUN ./gradlew clean build
ADD /application/build/libs/application-0.0.1-SNAPSHOT.jar  application-0.0.1-SNAPSHOT.jar

ENV SPRING_PROFILE="dev"

ENTRYPOINT ["sh", "-c", "java -jar  -Dspring.profiles.active=${SPRING_PROFILE}  application-0.0.1-SNAPSHOT.jar"]
