from fastapi import  Path, APIRouter,Depends, FastAPI, HTTPException
from typing import List
from sqlalchemy.orm import Session

import repo.schemas as schemas
import repo.connection as con
import application.crud as crud
# creates router
router = APIRouter(
    prefix="/api/v1",
    tags=["v1"],
    responses={404: {"description": "Not found"}},
)


@router.get("/")
def index():
    return "Welcome to the product service"

@router.get("/products/", response_model=List[schemas.Product])
def get_products(skip: int = 0, limit: int = 100, db: Session = Depends(con.get_db)):
    products = crud.get_products(db, skip=skip, limit=limit)
    return products


@router.post("/products/", response_model=schemas.Product)
def create_product(
    product: schemas.ProductCreate, db: Session = Depends(con.get_db)
):
    return crud.create_product(db=db, product=product)

# @router.get("/products/{id}")
# async def get_products_by_id(id:int = Path(None,description="Product Id",gt=0)):
#     response =  await get_products_by_id_api(id)
#     return response

# @router.get("/products/product-name/")
# async def get_products_by_name(*,name: Optional[str] = None):
#     if name:
#         response = await get_products_by_name_api(name=name)
#         return response
#     return {"Message":"Product Not Found"}