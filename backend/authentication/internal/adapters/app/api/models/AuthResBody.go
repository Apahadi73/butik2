package models

type AuthResBody struct{
	Id int `json:"id" xml:"id" form:"id"`
	Email string `json:"email" xml:"email" form:"email"`
	Token string `json:"token" xml:"token" form:"token"`
}