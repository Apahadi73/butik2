package authentication

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

func (apia Adapter) GetRegister(email , password string) (models.User,error){
	response, err:= apia.auth.Register(email,password)
	if err != nil{
		return response,err
	}
	rUser,err := apia.db.CreateUser(email,password)
	return rUser,nil
}  


func (apia Adapter) GetLogin(email , password string) (models.User,error){
	// check whether user exists in user table or not
	dbUser,err := apia.db.QueryUserByEmail(email)

	if err != nil{
		return dbUser,err
	}

	response, err:= apia.auth.Authenticate(password,dbUser.password)

	return response,nil
}  