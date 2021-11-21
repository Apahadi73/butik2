from fastapi import FastAPI
import routes.routes as r
import uvicorn
# create our application
app = FastAPI(title="Products Service")

@app.get("/")
def welcome():
    return "welcome"

# sets up router
app.include_router(r.router)


if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=5000)