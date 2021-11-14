package authentication

import "net/mail"

type Adapter struct{

}

func NewAdapter() *Adapter{
	return &Adapter{}
}

// func (auth Adapter) Register(email string, password string) (string,error){
// 	isValid := isEmailValid(email)
// 	if (isValid){
// 		return 
// 	}
// 	return "New user registered",nil;
// }  


// func (auth Adapter) Authenticate(uPassword , dbPassword string) (string,error){
// 	if uPassword == dbPassword{
// 		return "User authenticated",nil;

// 	}
// 	return "Password does not match",nil;
// }  

//  checks whether the email is valid or not
func (auth Adapter) CheckEmailValidity(email string) bool {
    _, err := mail.ParseAddress(email)
    return err == nil
}