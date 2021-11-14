package ports

import "butik/backend/authentication/internal/models"
type AuthenticationPort interface {
	// method signatures
	Register(email string, password string) (models.User,error)
	Authenticate(uPassword,dbPassword string) (string,error)
}