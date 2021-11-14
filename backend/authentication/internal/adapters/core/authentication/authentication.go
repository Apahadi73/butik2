package authentication

import "net/mail"

type Adapter struct{

}

func NewAdapter() *Adapter{
	return &Adapter{}
}

//  checks whether the email is valid or not
func (auth Adapter) CheckEmailValidity(email string) bool {
    _, err := mail.ParseAddress(email)
    return err == nil
}

// //  generates a new jwt token and returns the token
// func (auth Adapter) GenerateToken(email string, password string) (string,error) {
//     _, err := mail.ParseAddress(email)
//     return err == nil
// }

