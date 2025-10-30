# ==============================
# Stage 1: Build
# ==============================
FROM public.ecr.aws/docker/library/amazoncorretto:21-alpine AS build

WORKDIR /app

COPY . ./
RUN ./gradlew clean webapp-example:build --no-daemon -x test



# ==============================
# Stage 2: Runtime
# ==============================
FROM public.ecr.aws/docker/library/amazoncorretto:21-alpine
WORKDIR /app
COPY --from=build /app/webapp-example/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
