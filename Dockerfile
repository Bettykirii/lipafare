FROM gradle:5.6.4-jdk11 AS builder-env
USER root
WORKDIR /cuba
COPY . .
#RUN chmod +x gradlew

#reinstalls gradle wrapper since the one in the project is broken (CRLF issue)

#RUN gradle wrapper

RUN gradle buildUberJar -PpremiumRepoUser=201220019187 -PbintrayPremiumRepoUser=201220019187@cuba-platform -PpremiumRepoPass=FPGhEycTYnhW --no-daemon


FROM openjdk:11.0.9.1-jre-buster
ENV TZ=Africa/Nairobi
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
WORKDIR /lipafare
USER root
COPY --from=builder-env /cuba/build/distributions/uberJar/lipafare.jar .
CMD ["java", "-jar", "lipafare.jar", "-contextName", "app"]