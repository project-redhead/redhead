FROM maven:3.5.2-jdk-8-alpine as MAVEN_STAGE
COPY . /tmp/
WORKDIR /tmp/
RUN mvn package

FROM tomcat:9.0-jre8-alpine
EXPOSE 8080/tcp
COPY --from=MAVEN_STAGE /tmp/target/DHBWProject.war $CATALNINA_HOME/webapps/app.war
CMD ["catalina.sh", "run"]
