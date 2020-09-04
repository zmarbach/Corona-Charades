FROM tomcat:8.0-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY target/ROOT.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]
