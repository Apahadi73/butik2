package ports
type AuthenticationPort interface {
	// method signatures
	// Register(email string, password string) (models.User,error)
	// Authenticate(uPassword,dbPassword string) (models.User,error)
	CheckEmailValidity(email string) bool
}