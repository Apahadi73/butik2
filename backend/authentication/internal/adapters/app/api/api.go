package api

import (
	"butik/backend/authentication/internal/adapters/app/api/models"
	"butik/backend/authentication/internal/ports"
	"errors"
)

type Adapter struct{
	auth ports.AuthenticationPort
	db ports.DbPort
}

func NewAdapter(auth ports.AuthenticationPort, db ports.DbPort ) *Adapter{
	return &Adapter{auth: auth,db: db}
}

func (apia Adapter) Register(email , password string) (*models.AuthResBody,error){
	isEmailValid := apia.auth.CheckEmailValidity(email)
	
	// throws error if supplied email is invalid
	if !isEmailValid{
		return nil,errors.New("invalid email")
	}
	// response, err:= apia.auth.Register(email,password)
	// if err != nil{
	// 	return nil,err
	// }
	dbUser,err := apia.db.CreateUser(email,password)
	if err != nil{
		return nil,err
	}

	// creates response object and returns the created res object
	response:= models.AuthResBody{Id: dbUser.Id, Email: dbUser.Email}
	return &response,nil
}  


func (apia Adapter) Login(email , password string) (*models.AuthResBody,error){
	isEmailValid := apia.auth.CheckEmailValidity(email)
	
	// throws error if supplied email is invalid
	if !isEmailValid{
		return nil,errors.New("invalid email")
	}

	// check whether user exists in user table or not
	dbUser,err := apia.db.QueryUserByEmail(email)

	if err != nil{
		return nil,err
	}

	// creates response object and returns the created res object
	response:= models.AuthResBody{Id: dbUser.Id, Email: dbUser.Email}

	return &response,nil
}  