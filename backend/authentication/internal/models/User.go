package models

type User struct {
    email string
    password  string
}

// creates new user and return the pointer to the user
func NewUser(email,password string) *User {
	user:=User{email: email,password: password}
	return &user
}