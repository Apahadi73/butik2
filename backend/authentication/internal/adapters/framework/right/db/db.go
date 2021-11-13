package db

import (
	"butik/backend/authentication/internal/models"
	"database/sql"
	"log"

	sq "github.com/Masterminds/squirrel"
	// blank import for mysql driver
	_ "github.com/go-sql-driver/mysql"
)

// Adapter implements the DbPort interface
type Adapter struct {
	db *sql.DB
}

// NewAdapter creates a new Adapter
func NewAdapter(driverName, dataSourceName string) (*Adapter, error) {
	// connect
	db, err := sql.Open(driverName, dataSourceName)
	if err != nil {
		log.Fatalf("db connection failur: %v", err)
	}

	// test db connection
	err = db.Ping()
	if err != nil {
		log.Fatalf("db ping failure: %v", err)
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
func (da Adapter) CreateUser( email , password string) (models.User,error) {
	queryString, args, err := sq.Insert("users").Columns("email", "password").
		Values( email, password).ToSql()
	rUser := models.NewUser(email,password)

	if err != nil {
		return *rUser,err
	}

	_, err = da.db.Exec(queryString, args...)
	if err != nil {
		return *rUser,err
	}
	return *rUser,nil
}