package main

import (
	"butik/backend/authentication/internal/adapters/core/authentication"
	"fmt"
)

func main()  {
	auth := authentication.NewAdapter()
	response, err := auth.Login("amir","pahadi")
	if err != nil{
		fmt.Println(err)
	}
	fmt.Println(response)
}