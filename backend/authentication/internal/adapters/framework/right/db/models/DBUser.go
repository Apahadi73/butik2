package models

import "fmt"

// Model for instance of user table
type DBUser struct {
    Id     int64
    Email string
	Password string
}


func (u DBUser) String() string {
    return fmt.Sprintf("User<%d %s %v>", u.Id, u.Email,u.Password)
}