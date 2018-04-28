FROM openjdk:8-jre-alpine
MAINTAINER yaweiw@microsoft.com
RUN apk add --update bash && rm -rf /var/cache/apk/*

ADD ./packages/ /packages/

RUN chmod +x /packages/docker_run.sh
RUN dos2unix /packages/docker_run.sh

CMD /bin/bash "/packages/docker_run.sh"

EXPOSE 8888 8761
