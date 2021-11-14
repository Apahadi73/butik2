package handlers

import (
	"github.com/gofiber/fiber/v2"
)

// registers new user
func Register(c *fiber.Ctx) error{
	 return c.SendString("Hello, World 👋! for the v1 register")
}

// login already existing user
func  Login(c *fiber.Ctx) error {
	 return c.SendString("Hello, World 👋! for the v1 register")
}