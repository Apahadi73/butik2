package api

import (
	"butik/backend/authentication/internal/models"
	"butik/backend/authentication/internal/ports"
)

type Adapter struct{
	auth ports.AuthenticationPort
	db ports.DbPort
}

func NewAdapter(auth ports.AuthenticationPort, db ports.DbPort ) *Adapter{
	return &Adapter{auth: auth,db: db}
}

func (apia Adapter) Register(email , password string) (models.User,error){
	response, err:= apia.auth.Register(email,password)
	if err != nil{
		return response,err
	}
	rUser,err := apia.db.CreateUser(email,password)
	return rUser,nil
}  


func (apia Adapter) Login(email , password string) (models.User,error){
	// check whether user exists in user table or not
	dbUser,err := apia.db.QueryUserByEmail(email)

	if err != nil{
		return dbUser,err
	}

	//  authenticates the user password against the password saved in db
	response, err:= apia.auth.Authenticate(password,dbUser.password)

	if err != nil{
		return dbUser,err
	}

	return dbUser,nil
}  