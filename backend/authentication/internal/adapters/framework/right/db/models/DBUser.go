package models

import "fmt"

// Model for instance of user table
type DBUser struct {
    TableName struct{} `pg:"db_users"`
    Id     int   `json:"Id" pg:"Id,pk,unique"`
    Email string   `json:"email" pg:"email,notnull,unique"`
	Password string   `json:"password" pg:"password,notnull"`
}


func (u DBUser) String() string {
    return fmt.Sprintf("User<db_users %d %s %v>", u.Id, u.Email,u.Password)
}