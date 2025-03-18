FROM sbtscala/scala-sbt:eclipse-temurin-21.0.6_7_1.10.11_3.3.5 AS build-java

RUN apt-get update && apt-get install -y unzip curl
RUN curl -fsSL https://deb.nodesource.com/setup_22.x -o nodesource_setup.sh
RUN bash nodesource_setup.sh
RUN apt-get install -y nodejs
ENV JAVA_OPTS="-Xms6048m -Xmx10000m"

COPY ./acebocs-demo /acebocs-demo

WORKDIR /acebocs-demo

RUN sbt clean
RUN sbt playUpdateSecret && sbt dist

RUN cd /acebocs-demo/target/universal/ && unzip acebocs-demo-1.0-SNAPSHOT.zip

FROM eclipse-temurin:21-jre

COPY --from=build-java /acebocs-demo/target/universal/acebocs-demo-1.0-SNAPSHOT /acebocs-demo

WORKDIR /acebocs-demo

EXPOSE 9000

ENTRYPOINT [ "bin/acabocs-demo" ]