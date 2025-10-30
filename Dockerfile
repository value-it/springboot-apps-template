# ==============================
# Stage 1: Build
# ==============================
FROM public.ecr.aws/amazoncorretto/amazoncorretto:21-al2023-jdk AS build

WORKDIR /app

COPY . ./
RUN ./gradlew clean webapp-example:build --no-daemon -x test



# ==============================
# Stage 2: Runtime
# ==============================
FROM public.ecr.aws/amazoncorretto/amazoncorretto:21-al2023-headless
WORKDIR /app
COPY --from=build /app/webapp-example/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
