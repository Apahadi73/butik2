package db

import (
	"butik/backend/authentication/internal/models"
	"fmt"
	"log"

	"github.com/go-pg/pg"
)


type User struct {
    Id     int64
    email string
	password string
}

func (u User) String() string {
    return fmt.Sprintf("User<%d %s %v>", u.Id, u.email,u.password)
}

// Adapter implements the DbPort interface
type Adapter struct {
	db *pg.DB
}

// NewAdapter creates a new Adapter
func NewAdapter(driverName, dataSourceName string) (*Adapter, error) {
	// connect
	db := pg.Connect(&pg.Options{
		Addr:     "localhost",
		User:     "postgres",
		Password: "password",
		Database: "authentication_db",
	})
	
	return &Adapter{db: db}, nil
}

// CloseDbConnection closes the db  connection
func (da Adapter) CloseDbConnection() {
	err := da.db.Close()
	if err != nil {
		log.Fatalf("db close failure: %v", err)
	}
}

// adds new user to the user table
func (da Adapter) CreateUser( email , password string) (models.User,error) {
	rUser := models.NewUser(email,password)
	// create a new user in the user table
	_, err := da.db.Model(&User{
        email: email,
        password: password,
    }).Insert()

	if err != nil {
		return *rUser,err
	}
	return *rUser,nil
}


// query user table by email
// func (da Adapter) QueryUserByEmail( email , password string) (models.User,error) {
// 	_, err := da.db.Model(&User{
//         email: email,
//         password: password,
//     }).Insert()

// 	if err != nil {
// 		return *rUser,err
// 	}
// 	return *rUser,nil
// }