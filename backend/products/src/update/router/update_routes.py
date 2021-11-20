from fastapi import FastAPI, Path, APIRouter
from typing import Optional
from ..repo.models.product import Product
from ..application.update_api import update_api
    
# creates router
router = APIRouter(
    prefix="/api/v1",
    tags=["v1"],
    responses={404: {"description": "Not found"}},
)

@router.put("/products/")
async def update_product_api(product:Product):
    response = await update_api(product)
    return response


