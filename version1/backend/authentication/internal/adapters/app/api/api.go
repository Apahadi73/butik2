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

func (apia Adapter) Register(email , password, name string) (*models.AuthResBody ,error){

	// validates email and password
	isValid,err := apia.auth.ValidateAuthReq(email,password)
	if !isValid{
		return nil,err
	}
	// check whether user exists in user table or not
	user,_ := apia.db.QueryUserByEmail(email)
	if user != nil{
		return nil, errors.New("account with this email address already exists")
	}

	// creates a new user
	dbUser,err := apia.db.CreateUser(email,password,name )
	if err != nil{
		return nil,err
	}

	token,err:=apia.auth.GenerateToken(dbUser.Id,dbUser.Email,dbUser.Password)
	if err != nil{
		return nil,err
	}
	authRes:=models.AuthResBody{Name: dbUser.Name,Email: dbUser.Email,Token: token, Id: dbUser.Id}
	return &authRes,nil
}  


func (apia Adapter) Login(email , password string) (*models.AuthResBody ,error){

	// validates email and password
	isValid,err := apia.auth.ValidateAuthReq(email,password)
	if !isValid{
		return nil,err
	}

	// check whether user exists in user table or not
	dbUser,err := apia.db.QueryUserByEmail(email)

	if err != nil{
		return nil,err
	}

	token,err:=apia.auth.GenerateToken(dbUser.Id,dbUser.Email,dbUser.Password)
	if err != nil{
		return nil,err
	}

	authRes:=models.AuthResBody{Name: dbUser.Name,Email: dbUser.Email,Token: token, Id: dbUser.Id}
	return &authRes,nil
}  

