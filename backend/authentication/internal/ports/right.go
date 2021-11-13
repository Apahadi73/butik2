package ports

import "butik/backend/authentication/internal/models"

// DbPort is the port for a db adapter
type DbPort interface {
	CloseDbConnection()
	CreateUser(email , password string) (models.User,error)
	QueryUserByEmail(email string) (models.User,error)
}