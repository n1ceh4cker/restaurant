#
# Build stage
#
FROM maven:3.9.9-eclipse-temurin-22-alpine AS build
ARG mongodbUrl
ARG mailUsername
ARG mailPassword
ARG mailHost
ARG mailPort
ARG port
ENV MONGO_URL=$mongodbUrl
ENV MAIL_USERNAME=$mailUsername
ENV MAIL_PASSWORD=$mailPassword
ENV MAIL_HOST=$mailHost
ENV MAIL_PORT=$mailPort
ENV PORT=$port
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DMONGO_URL=${MONGO_URL} -DMAIL_USERNAME=${MAIL_USERNAME} -DMAIL_PASSWORD=${MAIL_PASSWORD} -DMAIL_HOST=${MAIL_HOST} -DMAIL_PORT=${MAIL_PORT} -DPORT=${PORT}
RUN echo -e "#!bin/sh \n java -jar -DMONGO_URL=${MONGO_URL} -DMAIL_USERNAME=${MAIL_USERNAME} -DMAIL_PASSWORD=${MAIL_PASSWORD} -DMAIL_HOST=${MAIL_HOST} -DMAIL_PORT=${MAIL_PORT} -DPORT=${PORT} /usr/local/lib/restaurant.jar" > /home/app/entrypoint.sh

#
# Package stage
#
FROM eclipse-temurin:22-jre-alpine
COPY --from=build /home/app/target/*.jar /usr/local/lib/restaurant.jar
COPY --from=build /home/app/entrypoint.sh ./entrypoint.sh
RUN chmod +x ./entrypoint.sh
EXPOSE 10000
ENTRYPOINT ["./entrypoint.sh"]