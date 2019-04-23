FROM maven:3.5.2

USER root

EXPOSE 9990 8080 8181

ENV PROJECT_HOME /etc/relationship

COPY ./target /etc/relationship/target

RUN mkdir -p /etc/relationship/config

WORKDIR ${PROJECT_HOME}

CMD ["java", "-jar",  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5006", "target/itrelationship-thorntail.jar"]