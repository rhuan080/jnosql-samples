FROM maven:3.5.2

USER root

EXPOSE 9990 8080 8181

ENV PROJECT_HOME /etc/itcatalog

COPY ./target /etc/itcatalog/target

RUN mkdir -p /etc/itcatalog/config

WORKDIR ${PROJECT_HOME}

CMD ["java", "-jar",  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5006 -Dswarm.bind.address=0.0.0.0", "target/itcatalog-thorntail.jar"]

