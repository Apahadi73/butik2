package ports

import "butik/backend/authentication/internal/adapters/framework/right/db/models"

// DbPort is the port for a db adapter
type DbPort interface {
	CloseDbConnection()
	CreateUser(email , password, name string) (*models.DBUser,error)
	QueryUserByEmail(email string) (*models.DBUser,error)
}