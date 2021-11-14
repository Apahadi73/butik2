package ports

type APIPort interface {
	Register(email , password string) (string,error)
	Login(email , password string) (string,error)
}