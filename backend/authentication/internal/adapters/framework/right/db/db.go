package db

import (
	"butik/backend/authentication/internal/adapters/framework/right/db/models"
	"log"

	"github.com/go-pg/pg"
	"github.com/go-pg/pg/orm"
)

// Adapter implements the DbPort interface
type Adapter struct {
	db *pg.DB
}

// NewAdapter creates a new Adapter
func NewAdapter() (*Adapter, error) {
	// connect
	db := pg.Connect(&pg.Options{
		Addr:     "db:5432",
		User:     "postgres",
		Password: "password",
		Database: "postgres",
	})
	err := createSchema(db)
    if err != nil {
        panic(err)
    }
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
func (da Adapter) CreateUser( email , password string) (*models.DBUser,error) {
	rUser := models.DBUser{Email: email,Password: password}
	// create a new user in the user table
	_, err := da.db.Model(&models.DBUser{
        Email: email,
        Password: password,
    }).Insert()

	if err != nil {
		return &rUser,err
	}

	return &rUser,nil
}


// query user table by email
func (da Adapter) QueryUserByEmail(email string) (*models.DBUser,error) {
	// Select user by email.
    user := new(models.DBUser)

	// queries user table using provided email address
    err:= da.db.Model(user).Where("email = ?",email).Select()
    if err != nil {
        return nil,err
    }
	
	rUser:=models.DBUser{Email: user.Email}
	return &rUser,nil
}


// createSchema creates database schema for User and Story models.
func createSchema(db *pg.DB) error {
    models := []interface{}{
        (*models.DBUser)(nil),
    }

    for _, model := range models {
        err := db.Model(model).CreateTable(&orm.CreateTableOptions{
            Temp: true,
        })
        if err != nil {
            return err
        }
    }
    return nil
}