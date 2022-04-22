FROM --platform=linux/amd64 amazoncorretto:17
VOLUME /tmp
RUN mkdir /application
WORKDIR /application
COPY ./build/libs/ /application/

ENV JAR_TARGET "webapp-example.jar"
ENTRYPOINT ["sh","-c","java -jar -Dspring.profiles.active=without_db ./${JAR_TARGET}"]