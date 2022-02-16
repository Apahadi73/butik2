package ports
type AuthenticationPort interface {
	// method signatures
	// Authenticate(uPassword,dbPassword string) (models.User,error)
	CheckEmailValidity(email string) bool
	ValidateAuthReq(email, password string) (bool,error)
	GenerateToken(Id int,email string, password string) (string,error)
}