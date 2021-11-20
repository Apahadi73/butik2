from typing import List
from fastapi import Depends, FastAPI, HTTPException
from sqlalchemy.orm import Session

# from .repo import models, schemas
# from .repo.database import SessionLocal, engine
# from .application import crud
import repo.database as repo_db
import repo.models as models
import repo.schemas as schemas
import application.crud as crud

models.Base.metadata.create_all(bind=repo_db.engine)

app = FastAPI()

# We need to have an independent database session/connection (SessionLocal) per request, 
# use the same session through all the request and then close it after the request is finished.
# And then a new session will be created for the next request.
# Dependency
def get_db():
    db = repo_db.SessionLocal()
    try:
        yield db
    finally:
        db.close()

@app.get("/")
def index():
    return "Welcome to the product service"

@app.post("/products/", response_model=schemas.Product)
def create_product(
    product: schemas.ProductCreate, db: Session = Depends(get_db)
):
    return crud.create_product(db=db, product=product)

@app.get("/products/", response_model=List[schemas.Product])
def read_products(skip: int = 0, limit: int = 100, db: Session = Depends(get_db)):
    products = crud.get_products(db, skip=skip, limit=limit)
    return products
