FROM --platform=linux/amd64 amazoncorretto:21
VOLUME /tmp
RUN mkdir /application
WORKDIR /application
COPY ./build/libs/ /application/

ENV JAR_TARGET "webapp-example.jar"
ENTRYPOINT ["sh","-c","java -jar ./${JAR_TARGET}"]