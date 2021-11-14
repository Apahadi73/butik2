package routes

import (
	"butik/backend/authentication/internal/adapters/framework/left/http_server/handlers"

	"github.com/gofiber/fiber/v2"
)

// sets up all the route handlers
func SetupRoutes(v1 fiber.Router) {
	v1.Get("/register", handlers.Register)
    v1.Get("/login", handlers.Login)
}