package ports

type AuthenticationPort interface {
	// method signatures
	Register(email string, password string) (string,error)
	Login(email string, password string) (string,error)
}