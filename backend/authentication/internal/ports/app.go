package ports

import "butik/backend/authentication/internal/adapters/app/api/models"

type APIPort interface {
	Register(email , password string) (*models.AuthResBody,error)
	Login(email , password string) (*models.AuthResBody,error)
}