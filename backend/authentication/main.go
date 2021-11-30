package main

import (
	"butik/backend/authentication/internal/adapters/app/api"
	"butik/backend/authentication/internal/adapters/core/authentication"
	"butik/backend/authentication/internal/adapters/framework/left/http_server"
	"butik/backend/authentication/internal/adapters/framework/right/db"
	"butik/backend/authentication/internal/ports"
	"fmt"
	"log"
)

func main()  {
	var dbaseAdapter ports.DbPort
	var authAdapter ports.AuthenticationPort
	var appAdapter ports.APIPort
	var server ports.HttpServer

	// err := godotenv.Load()
	// if err!=nil {
	// 	log.Fatal(err)
	// }

	// sets up database
	dbaseAdapter,err :=db.NewAdapter()
	if err!=nil {
		log.Fatalf("failed to initiate dbase connection: %v",err)
	}

	// sets up authentication/core
	authAdapter = authentication.NewAdapter()

	// sets up application adapter
	appAdapter = api.NewAdapter(authAdapter,dbaseAdapter)

	server =http_server.NewAdapter(appAdapter)

	server.Start()
	if err != nil{
		fmt.Println(err)
	}

	defer dbaseAdapter.CloseDbConnection()
}