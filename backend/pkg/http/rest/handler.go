package rest

import "github.com/gorilla/mux"

func InitHandlers() *mux.Router  {
	router:= mux.NewRouter()
	router.HandleFunc("/api/",welcoleHandler).Methods(("GET"))
	return router
}