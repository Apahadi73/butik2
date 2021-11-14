package handlers

import (
	"butik/backend/authentication/internal/ports"
	"fmt"
	"log"

	"github.com/gofiber/fiber/v2"
)

type AuthBody struct {
    email string `json:"email" xml:"email" form:"email"`
    password string `json:"email" xml:"email" form:"email"`   
}

// welcome handler
func WelcomeHandler(c *fiber.Ctx) error{
	 return c.SendString("Welcome to the authentication service v1")
}
// registers new user
func Register(c *fiber.Ctx,api ports.APIPort) error{
	body := new(AuthBody)
	fmt.Println(c)
	if err := c.BodyParser(body); err != nil {
		return c.Status(503).SendString(err.Error())
	}
	log.Println(body) 
	log.Println(body.password) 
	fmt.Println(body.email)

	// an anonymous struct to parse the body
	// body := struct{
	// 	email string
	// 	password string
	// }{}
	// if err := c.BodyParser(body); err != nil {
	// 	panic(err)
	// }
	// return c.SendString("Hello, World 👋! for the v1 register")
	return c.Send(c.Body())
}

// login already existing user
func  Login(c *fiber.Ctx,api ports.APIPort) error {
	 return c.SendString("Hello, World 👋! for the v1 register")
}