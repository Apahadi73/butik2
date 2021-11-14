package ports
type AuthenticationPort interface {
	// method signatures
	Register(email string, password string) (string,error)
	Authenticate(uPassword,dbPassword string) (string,error)
}