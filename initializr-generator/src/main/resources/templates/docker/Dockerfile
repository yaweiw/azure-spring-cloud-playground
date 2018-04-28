FROM openjdk:8-jre-alpine
MAINTAINER yaweiw@microsoft.com
RUN apk add --update bash && rm -rf /var/cache/apk/*

ADD ./packages/ /packages/
ADD docker_run.sh /packages/

EXPOSE 8888 8761
