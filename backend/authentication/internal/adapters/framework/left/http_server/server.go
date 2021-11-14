package http_server

import (
	"butik/backend/authentication/internal/adapters/framework/left/http_server/routes"
	"fmt"
	"log"
	"os"

	"github.com/gofiber/fiber"
	"github.com/joho/godotenv"
)

// Adapter implements the DbPort interface
type Adapter struct {
	// api ports.APIPort
}

// returns a new http server adapter
// func NewAdapter(api ports.APIPort) *Adapter{
// 	return &Adapter{api: api}
// }

func NewAdapter() * Adapter{
	return &Adapter{}
}

func (server Adapter) Start(){
	err := godotenv.Load()
	if err!=nil {
		log.Fatal(err)
	}

	// creates new fiber application
	app := fiber.New()

	// sets up routes
	routes.SetupRoutes(app)

	// extract port from environment variable
	PORT := os.Getenv("PORT")
    log.Fatal(app.Listen(fmt.Sprintf("localhost:%v",PORT)))
}





