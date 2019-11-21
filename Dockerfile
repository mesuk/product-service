From tomcat:8.0.51-jre8-alpine
EXPOSE 9090
RUN sed -i 's/port="8080"/port="9090"/' /usr/local/tomcat/conf/server.xml
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./build/libs/product-service.war /usr/local/tomcat/webapps/product-service.war
CMD ["catalina.sh","run"]
