package api

import (
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

func (apia Adapter) Register(email , password string) (string,error){

	// validates email and password
	isValid,err := apia.auth.ValidateAuthReq(email,password)
	if !isValid{
		return "",err
	}
	// check whether user exists in user table or not
	user,_ := apia.db.QueryUserByEmail(email)
	if user != nil{
		return "", errors.New("account with this email address already exists")
	}

	// creates a new user
	dbUser,err := apia.db.CreateUser(email,password)
	if err != nil{
		return "",err
	}

	token,err:=apia.auth.GenerateToken(dbUser.Id,dbUser.Email,dbUser.Password)
	if err != nil{
		return "",err
	}
	return token,nil
}  


func (apia Adapter) Login(email , password string) (string,error){

	// validates email and password
	isValid,err := apia.auth.ValidateAuthReq(email,password)
	if !isValid{
		return "",err
	}

	// check whether user exists in user table or not
	dbUser,err := apia.db.QueryUserByEmail(email)

	if err != nil{
		return "",err
	}

	token,err:=apia.auth.GenerateToken(dbUser.Id,dbUser.Email,dbUser.Password)
	if err != nil{
		return "",err
	}
	return token,nil
}  

