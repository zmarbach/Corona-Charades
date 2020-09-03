FROM tomcat:8.0
COPY /home/zmarbach/source/Corona-Charades/target/ROOT.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]