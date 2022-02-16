FROM openjdk:9-jdk
EXPOSE 5003:5003
RUN mkdir /app
COPY ./build/install/com.example.cart/ /app/
WORKDIR /app/bin
CMD ["./com.example.cart"]