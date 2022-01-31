FROM openjdk:8-jdk
EXPOSE 5003:5003
RUN mkdir /app \
./gradlew installDist
COPY ./build/install/com.example.cart/ /app/
WORKDIR /app/bin
CMD ["./cart"]