# from fastapi import FastAPI, Path, APIRouter
# from fastapi_versioning import VersionedFastAPI, version
# from typing import Optional
# from pydantic import BaseModel
# from src.dummy_data import products
# from src.models.product import Product
# from src
    
# # creates router
# router = APIRouter(
#     prefix="/api/v1",
#     tags=["v1"],
#     responses={404: {"description": "Not found"}},
# )

# @router.get("/products/")
# def get_products():
#     get_products_api()

# @router.get("/products/{id}")
# def get_products_by_id(id:int = Path(None,description="Product Id",gt=0)):
#     get_products_by_id(id)
    

# @router.get("/products/product-name/")
# async def get_products_by_name(*,name: Optional[str] = None):
#     for product_id in products.products:
#         if products.products[product_id]["name"] == name:
#             return  products.products[product_id]
#     return {"Data":"Not Found"}


# @router.post("/products/")
# async def create_product(product:Product):
#     products.products[19]=product
#     return products.products


# @router.put("/products/{id}")
# async def update_product(id:int,product:Product):
#     if id not in products.products:
#         return {"Data":"Not Found"}
#     else:
#         products.products[id]=product
#         return products.products[id]
    