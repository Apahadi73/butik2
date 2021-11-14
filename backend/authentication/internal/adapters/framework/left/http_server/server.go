package http_server

import (
	"butik/backend/authentication/internal/adapters/framework/left/http_server/routes"
	"butik/backend/authentication/internal/ports"
	"fmt"
	"log"
	"os"

	"github.com/gofiber/fiber/v2"
	"github.com/gofiber/fiber/v2/middleware/cors"
	"github.com/joho/godotenv"
)

// Adapter implements the DbPort interface
type Adapter struct {
	api ports.APIPort
}

// returns a new http server adapter
func NewAdapter(api ports.APIPort) *Adapter{
	return &Adapter{api: api}
}

// starts our http server
func (server Adapter) Start(){
	err := godotenv.Load()
	if err!=nil {
		log.Fatal(err)
	}

	// creates new fiber application
	app := fiber.New()
	app.Use(cors.New())

	// sets up api version
	api:=app.Group("/api/authentication")
	v1 := api.Group("/v1", func(c *fiber.Ctx) error {
		c.Set("Version","v1")
		return c.Next()
	})

	// sets up routes for the first version of api
	routes.SetupRoutes(v1,server.api)

	// extract port from environment variable
	PORT := os.Getenv("PORT")
	fmt.Printf("server is listening on port: %v",PORT)
    log.Fatal(app.Listen(fmt.Sprintf(":%v",PORT)))
}




