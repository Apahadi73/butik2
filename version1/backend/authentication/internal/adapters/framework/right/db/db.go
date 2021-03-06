package db

import (
	"butik/backend/authentication/internal/adapters/framework/right/db/models"
	"fmt"
	"log"
	"os"

	"github.com/go-pg/pg"
	"github.com/go-pg/pg/orm"
)

// Adapter implements the DbPort interface
type Adapter struct {
	db *pg.DB
}

// NewAdapter creates a new Adapter
func NewAdapter() (*Adapter, error) {
	HOST := os.Getenv("HOST")
	POSTGRES_PORT := os.Getenv("POSTGRES_PORT")
	POSTGRES_USER := os.Getenv("POSTGRES_USER")
	POSTGRES_PASSWORD := os.Getenv("POSTGRES_PASSWORD")
	POSTGRES_DATABASE := os.Getenv("POSTGRES_DATABASE")
	address := fmt.Sprintf("%s:%s", HOST, POSTGRES_PORT)
	options := &pg.Options{
		User:     POSTGRES_USER,
		Password: POSTGRES_PASSWORD,
		Addr:     address,
		Database: POSTGRES_DATABASE,
		PoolSize: 50,
	}
	// connect
	db := pg.Connect(options)
	if db == nil {
		log.Panic("cannot connect to postgres")
	}

	err := createSchema(db)
    if err != nil {
        panic(err)
    }
	fmt.Println("app is connected to db on address:",address)

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
func (da Adapter) CreateUser( email , password,name string) (*models.DBUser,error) {
	rUser := models.DBUser{Email: email,Password: password, Name: name}
	// create a new user in the user table
	_, err := da.db.Model(&rUser).Insert()

	if err != nil {
		return nil,err
	}

	return &rUser,nil
}


// query user table by email
func (da Adapter) QueryUserByEmail(email string) (*models.DBUser,error) {
	// Select user by email.
    rUser:=models.DBUser{}
	// queries user table using provided email address
    err:= da.db.Model(&rUser).Where("Email = ?",email).First()
    if err != nil {
        return nil,err
    }
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