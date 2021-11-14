package models

type User struct {
    Email string `json:"email" xml:"email" form:"email"`
    Password string `json:"password" xml:"password" form:"password"`
}

// creates new user and return the pointer to the user
func NewUser(email,password string) *User {
	user:=User{Email: email,Password: password}
	return &user
}