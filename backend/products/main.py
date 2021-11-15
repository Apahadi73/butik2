from fastapi import FastAPI, Path
from typing import Optional
from pydantic import BaseModel
from dummy_data import products
from src.routers import products
from src.read.router import read_router

# create our application
app = FastAPI(title="Products Service")

# sets up router
app.include_router(read_router.router)