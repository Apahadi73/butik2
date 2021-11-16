from ..repo.models.product import Product
from ..repo.dummy_data.products import products

async def create_product(product:Product):
    products[19]=product
    return products[19]