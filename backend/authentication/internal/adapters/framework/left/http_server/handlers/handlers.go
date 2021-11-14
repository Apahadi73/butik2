package handlers

import (
	"butik/backend/authentication/internal/models"
	"butik/backend/authentication/internal/ports"
	"fmt"

	"github.com/gofiber/fiber/v2"
)

// welcome handler

func WelcomeHandler(c *fiber.Ctx) error{
	 return c.SendString("Welcome to the authentication service v1")
}
// registers new user
func Register(c *fiber.Ctx,api ports.APIPort) error{
	fmt.Println(c.Body())
	body := new(models.User)
	if err := c.BodyParser(body); err != nil {
		fmt.Println("reached here")
		return c.Status(503).SendString(err.Error())
	}
	fmt.Println(*body)

	// an anonymous struct to parse the body
	// body := struct{
	// 	email string
	// 	password string
	// }{}
	// if err := c.BodyParser(body); err != nil {
	// 	panic(err)
	// }
	return c.SendString("Hello, World 👋! for the v1 register")
}

// login already existing user
func  Login(c *fiber.Ctx,api ports.APIPort) error {
	 return c.SendString("Hello, World 👋! for the v1 register")
}