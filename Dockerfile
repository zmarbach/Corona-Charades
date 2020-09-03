FROM tomcat:8.0-alpine
RUN rm /usr/local/tomcat/webapps/ROOT.war
COPY **/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
