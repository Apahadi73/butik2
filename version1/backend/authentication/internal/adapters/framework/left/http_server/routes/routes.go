package routes

import (
	"butik/backend/authentication/internal/adapters/framework/left/http_server/handlers"
	"butik/backend/authentication/internal/ports"

	"github.com/gofiber/fiber/v2"
)

// sets up all the route handlers
func SetupRoutes(v1 fiber.Router,api ports.APIPort) {
	v1.Post("/register", func(c *fiber.Ctx) error {return handlers.Register(c,api)})
    v1.Post("/login", func(c *fiber.Ctx) error {return handlers.Login(c,api)})
    v1.Get("/", handlers.WelcomeHandler)
}