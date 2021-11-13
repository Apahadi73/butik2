package authentication

type Adapter struct{

}

func NewAdapter() *Adapter{
	return &Adapter{}
}

func (auth Adapter) Register(email string, password string) (string,error){
	return "New user registered",nil;
}  


func (auth Adapter) Authenticate(uPassword , dbPassword string) (string,error){
	if uPassword == dbPassword{
		return "User authenticated",nil;

	}
	return "Password does not match",nil;
}  