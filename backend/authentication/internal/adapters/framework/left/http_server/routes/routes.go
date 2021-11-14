package routes

import (
	"butik/backend/authentication/internal/adapters/framework/left/http_server/handlers"

	"github.com/gofiber/fiber/v2"
)

// sets up all the route handlers
func SetupRoutes(app *fiber.App) {
	app.Get("/register", handlers.Register)
    app.Get("/login", handlers.Login)
}