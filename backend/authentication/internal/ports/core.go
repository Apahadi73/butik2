package ports
type AuthenticationPort interface {
	// method signatures
	// GenerateToken(email string, password string) (string,error)
	// Authenticate(uPassword,dbPassword string) (models.User,error)
	CheckEmailValidity(email string) bool
}