package main

import (
	"backend/pkg/http/rest"
	"fmt"
	"log"
	"net/http"
)

func main(){
	fmt.Println("Starting server on port 5000")
	router:= rest.InitHandlers()
	log.Fatal(http.ListenAndServe(":5000",router))
}