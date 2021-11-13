package ports

// DbPort is the port for a db adapter
type DbPort interface {
	CloseDbConnection()
	CreateUser(email , password string) (string,error)
	QueryUserByEmail(email string) (string,error)
}