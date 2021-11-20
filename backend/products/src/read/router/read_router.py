from fastapi import FastAPI, Path, APIRouter
from typing import Optional
from ..application.read_api import get_products_by_id_api,get_products_api,get_products_by_name_api
    
# creates router
router = APIRouter(
    prefix="/api/v1",
    tags=["v1"],
    responses={404: {"description": "Not found"}},
)

@router.get("/products/")
async def get_products():
    response = await get_products_api()
    return response

@router.get("/products/{id}")
async def get_products_by_id(id:int = Path(None,description="Product Id",gt=0)):
    response =  await get_products_by_id_api(id)
    return response

@router.get("/products/product-name/")
async def get_products_by_name(*,name: Optional[str] = None):
    if name:
        response = await get_products_by_name_api(name=name)
        return response
    return {"Message":"Product Not Found"}
        