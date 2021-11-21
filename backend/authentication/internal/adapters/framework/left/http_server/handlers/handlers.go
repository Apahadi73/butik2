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
	body := new(models.User)
	if err := c.BodyParser(body); err != nil {
		return c.Status(400).JSON(err.Error())
	}

	fmt.Println(body)

	// passes parsed email and password to the application layer
	token, err:= api.Register(body.Email,body.Password)

	if err != nil{
		return c.Status(400).JSON(err.Error())
	}
	m := make(map[string]string)
	m["token"] = token
	return c.JSON(m)
}

// login already existing user
func  Login(c *fiber.Ctx,api ports.APIPort) error {
	body := new(models.User)
	if err := c.BodyParser(body); err != nil {
		return c.Status(400).JSON(err.Error())
	}

	// passes parsed email and password to the application layer
	token, err:= api.Login(body.Email,body.Password)

	if err != nil{
		return c.Status(400).JSON(err.Error())
	}
	m := make(map[string]string)
	m["token"] = token
	return c.JSON(m)
}