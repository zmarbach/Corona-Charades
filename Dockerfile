FROM tomcat:8.0
RUN rm -fr /usr/local/tomcat/webapps/ROOT
COPY **/ROOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]