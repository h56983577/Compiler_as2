FROM openjdk:8
WORKDIR /src/
COPY ./* ./
RUN javac Analyser.java