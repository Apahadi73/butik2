package api

import (
	"butik/backend/authentication/internal/ports"
	"fmt"
)

type Adapter struct{
	auth ports.AuthenticationPort
	db ports.DbPort
}

func NewAdapter(auth ports.AuthenticationPort, db ports.DbPort ) *Adapter{
	return &Adapter{auth: auth,db: db}
}

func (apia Adapter) Register(email , password string) (string,error){
	response, err:= apia.auth.Register(email,password)
	if err != nil{
		return response,err
	}
	rUser,err := apia.db.CreateUser(email,password)
	fmt.Println(rUser)
	return response,nil
}  


func (apia Adapter) Login(email , password string) (string,error){
	// check whether user exists in user table or not
	// dbUser,err := apia.db.QueryUserByEmail(email)

	// if err != nil{
	// 	return "",err
	// }

	//  authenticates the user password against the password saved in db
	response, err:= apia.auth.Authenticate(password,password)

	if err != nil{
		return response,err
	}

	return response,nil
}  