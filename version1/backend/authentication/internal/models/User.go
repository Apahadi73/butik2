package models

type User struct {
    Email string `json:"email" xml:"email" form:"email"`
    Password string `json:"password" xml:"password" form:"password"`
	Name string `json:"name" xml:"name" form:"name"`
}

// creates new user and return the pointer to the user
func NewUser(email,password, name string) *User {
	user:=User{Email: email,Password: password, Name: name}
	return &user
}