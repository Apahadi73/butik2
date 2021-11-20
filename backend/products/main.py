from fastapi import FastAPI, Path
from typing import Optional
from pydantic import BaseModel
from dummy_data import products
from src.routers import products
from src.read.router import read_router
from src.create.router import create_router
from src.update.router import update_routes

# create our application
app = FastAPI(title="Products Service")

# sets up router
app.include_router(read_router.router)
app.include_router(create_router.router)
app.include_router(update_routes.router)