FROM openjdk:8-jdk
EXPOSE 5003:5003
RUN mkdir /app
COPY ./build/install/cart/ /app/
WORKDIR /app/bin
CMD ["./cart"]