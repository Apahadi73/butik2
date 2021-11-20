from fastapi import FastAPI
import routes.routes as r

# create our application
app = FastAPI(title="Products Service")

# sets up router
app.include_router(r.router)