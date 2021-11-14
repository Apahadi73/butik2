package main

import (
	"butik/backend/authentication/internal/adapters/core/authentication"
	"butik/backend/authentication/internal/adapters/framework/left/http_server"
	"fmt"
)

func main()  {
	auth := authentication.NewAdapter()
	response, err := auth.Authenticate("pahadi","pahadi")
	server:=http_server.NewAdapter()
	server.Start()
	if err != nil{
		fmt.Println(err)
	}
	fmt.Println(response)
}