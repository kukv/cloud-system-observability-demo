FROM gradle:9.3.1-jdk25-corretto AS builder

WORKDIR /workspace

RUN --mount=type=bind,source=build.gradle.kts,target=/workspace/build.gradle.kts \
    --mount=type=bind,source=settings.gradle.kts,target=/workspace/settings.gradle.kts \
    --mount=type=bind,source=gradle/libs.versions.toml,target=/workspace/gradle/libs.versions.toml \
    --mount=type=bind,source=app/api-caller/build.gradle.kts,target=/workspace/app/api-caller/build.gradle.kts \
    --mount=type=bind,source=app/order-api/build.gradle.kts,target=/workspace/app/order-api/build.gradle.kts \
    --mount=type=bind,source=app/product-api/build.gradle.kts,target=/workspace/app/product-api/build.gradle.kts \
    --mount=type=bind,source=app/user-api/build.gradle.kts,target=/workspace/app/user-api/build.gradle.kts \
    --mount=type=cache,target=/home/gradle/.gradle,sharing=locked \
    gradle dependencies --no-daemon --parallel

RUN --mount=type=bind,source=build.gradle.kts,target=/workspace/build.gradle.kts \
    --mount=type=bind,source=settings.gradle.kts,target=/workspace/settings.gradle.kts \
    --mount=type=bind,source=gradle/libs.versions.toml,target=/workspace/gradle/libs.versions.toml \
    --mount=type=bind,source=app/api-caller/build.gradle.kts,target=/workspace/app/api-caller/build.gradle.kts \
    --mount=type=bind,source=app/api-caller/src,target=/workspace/app/api-caller/src \
    --mount=type=bind,source=app/order-api/build.gradle.kts,target=/workspace/app/order-api/build.gradle.kts \
    --mount=type=bind,source=app/order-api/src,target=/workspace/app/order-api/src \
    --mount=type=bind,source=app/product-api/build.gradle.kts,target=/workspace/app/product-api/build.gradle.kts \
    --mount=type=bind,source=app/product-api/src,target=/workspace/app/product-api/src \
    --mount=type=bind,source=app/user-api/build.gradle.kts,target=/workspace/app/user-api/build.gradle.kts \
    --mount=type=bind,source=app/user-api/src,target=/workspace/app/user-api/src \
    --mount=type=cache,target=/home/gradle/.gradle,sharing=locked \
    --mount=type=cache,target=/workspace/build,sharing=locked \
    gradle build -x test --no-daemon --parallel --max-workers=4 --build-cache --configuration-cache

FROM eclipse-temurin:25-jre-alpine AS order-api

WORKDIR /app

COPY --from=builder /workspace/app/order-api/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:25-jre-alpine AS product-api

WORKDIR /app

COPY --from=builder /workspace/app/product-api/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:25-jre-alpine AS user-api

WORKDIR /app

COPY --from=builder /workspace/app/user-api/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:25-jre-alpine AS api-caller

WORKDIR /app

COPY --from=builder /workspace/app/api-caller/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
