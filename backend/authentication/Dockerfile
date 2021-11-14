FROM golang:1.16-alpine

RUN mkdir /app
WORKDIR /app

COPY . .
COPY go.mod .
COPY go.sum .
RUN go mod download

RUN go build -o cmd/main.go
RUN mv main /usr/local/bin/

CMD ["main"]