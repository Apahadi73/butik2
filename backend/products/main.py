from fastapi import FastAPI, Path
from typing import Optional
from pydantic import BaseModel
from dummy_data import products

# create our application
app = FastAPI()

class Product(BaseModel):
    name: Optional[str] = None
    image: Optional[str] = None
    description:Optional[str] = None
    brand:Optional[str] = None
    category: Optional[str] = None
    price: int = 0
    countInStock: int = 0
    rating: int = 0
    numReviews: int = 0
    
@app.get("/api/products")
def index():
    return "Welcome to the products service for v1"

@app.get("/api/products/")
def get_products():
    return products.products

@app.get("/api/products/{id}")
def get_products_by_id(id:int = Path(None,description="Product Id",gt=0)):
    response = products.products[id]
    return response

@app.get("/api/products/product-name/")
async def get_products_by_name(*,name: Optional[str] = None):
    for product_id in products.products:
        if products.products[product_id]["name"] == name:
            return  products.products[product_id]
    return {"Data":"Not Found"}


@app.post("/api/products/")
async def create_product(product:Product):
    products.products[19]=product
    return products.products


@app.put("/api/products/{id}")
async def create_product(id:int,product:Product):
    if id not in products.products:
        return {"Data":"Not Found"}
    else:
        products.products[id]=product
        return products.products[id]