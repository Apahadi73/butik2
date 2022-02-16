FROM alpine

# Installation
RUN echo 'http://dl-cdn.alpinelinux.org/alpine/v3.6/main' >> /etc/apk/repositories
RUN echo 'http://dl-cdn.alpinelinux.org/alpine/v3.6/community' >> /etc/apk/repositories

RUN apk update
RUN apk add mongodb=3.4.4-r0

# Configuration
VOLUME [ "/data/db" ]
WORKDIR /data
EXPOSE 27020

# set default commands
CMD ["mongod", "--port", "27020"]


# FROM ubuntu

# RUN apt-get update
# RUN apt-get -y install mongodb

# # Configuration
# VOLUME [ "/data/db" ]
# WORKDIR /data
# EXPOSE 27020

# # set default commands
# CMD ["mongod", "--port", "27020"]
