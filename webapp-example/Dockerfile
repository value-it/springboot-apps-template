FROM --platform=linux/amd64 amazoncorretto:17
VOLUME /tmp
RUN mkdir /application
WORKDIR /application
COPY ./build/libs/ /application/
COPY ./webapp-example.conf /application/

ENV JAR_TARGET "webapp-example.jar"
ENTRYPOINT ["sh","-c","java -jar ./${JAR_TARGET}", "--spring.profiles.active=without_db"]