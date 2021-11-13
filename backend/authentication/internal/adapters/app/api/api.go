package authentication

import "butik/backend/authentication/internal/ports"

type Adapter struct{
	auth ports.AuthenticationPort
}

func NewAdapter(auth ports.AuthenticationPort ) *Adapter{
	return &Adapter{auth: auth}
}

func (apia Adapter) GetRegister(email , password string) (string,error){
	response, err:= apia.auth.Register(email,password)
	if err != nil{
		return "Failed to register the user",err
	}
	return response,nil
}  


func (apia Adapter) GetLogin(email , password string) (string,error){
	response, err:= apia.auth.Login(email,password)
	if err != nil{
		return "Failed to login the user",err
	}
	return response,nil
}  