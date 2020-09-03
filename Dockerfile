FROM tomcat:8.0
RUN sudo cp /home/zmarbach/source/Corona-Charades/target/ROOT.war /opt/tomcat/apache-tomcat-9.0.37/webapps
EXPOSE 8080
CMD ["catalina.sh", "run"]