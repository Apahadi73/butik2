package ports

type APIPort interface {
	GetRegister(email , password string) (string,error)
	GetLogin(email , password string) (string,error)
}