From tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./build/libs/product-service.war.war /usr/local/tomcat/webapps/product-service.war
CMD ["catalina.sh","run"]