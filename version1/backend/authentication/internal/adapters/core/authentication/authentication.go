package authentication

import (
	"errors"
	"net/mail"
	"os"
	"time"

	"github.com/dgrijalva/jwt-go"
)

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


func (auth Adapter) ValidateAuthReq(email, password string) (bool,error){
	isEmailValid := auth.CheckEmailValidity(email)
	
	// throws error if supplied email is invalid
	if !isEmailValid{
		return false,errors.New("invalid email")
	}
	
	if password == "" {
		return false, errors.New("nvalid password")
	}

    if len(password) < 6 {
        return false, errors.New("password must be at least 6 characters in length")
    }

    return true, nil
}

//  generates a new jwt token and returns the token
func (auth Adapter) GenerateToken(Id int,email string, password string) (string,error) {
    var err error
    //Creating Access Token
    atClaims := jwt.MapClaims{}
    atClaims["authorized"] = true
    atClaims["Id"] = Id
    atClaims["email"] = email
    atClaims["exp"] = time.Now().Add(time.Minute * 15).Unix()
    at := jwt.NewWithClaims(jwt.SigningMethodHS256, atClaims)
    token, err := at.SignedString([]byte(os.Getenv("ACCESS_SECRET")))
    if err != nil {
        return "", err
    }
    return token, nil
}

