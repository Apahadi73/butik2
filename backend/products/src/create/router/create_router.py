from fastapi import FastAPI, Path, APIRouter
from typing import Optional
from ..repo.models.product import Product
from ..application.create_api import create_product
    
# creates router
router = APIRouter(
    prefix="/api/v1",
    tags=["v1"],
    responses={404: {"description": "Not found"}},
)

# creates new product and adds the newly created database to the products db
@router.post("/products/")
async def create_product_api(product:Product):
    response = await create_product(product)
    return response


