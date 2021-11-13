package authentication

type Adapter struct{

}

func NewAdapter() *Adapter{
	return &Adapter{}
}

func (auth Adapter) Register(email string, password string) (string,error){
	return "New user registered",nil;
}  


func (auth Adapter) Login(email string, password string) (string,error){
	return "User authenticated",nil;
}  