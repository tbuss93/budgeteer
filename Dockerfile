FROM tomcat:9.0.7-jre8
COPY budgeteer-web-interface/build/libs/budgeteer-web-interface-1.0.9.BETA-local.war /usr/local/tomcat/webapps/budgeteer.war