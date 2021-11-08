package rest

import (
	"encoding/json"
	"net/http"
)

func welcoleHandler(response http.ResponseWriter, request *http.Request)  {
	response.WriteHeader(http.StatusOK)
	json.NewEncoder(response).Encode("Welcome to the butik")
}