package ports

type GRPCPort interface{
	Run()
	GetLogin()
	GetRegister()
}