package handlers

import (
	"github.com/gofiber/fiber"
)

// registers new user
func Register(c *fiber.Ctx) {
	 c.SendString("Hello, World 👋! for the v1 register")
}

// login already existing user
func  Login(c *fiber.Ctx) {
	 c.SendString("Hello, World 👋! for the v1 register")
}