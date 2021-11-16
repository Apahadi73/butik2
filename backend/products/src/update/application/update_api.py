from ..repo.models.product import Product
from ..repo.dummy_data.products import products

# updates the provided product in the database
async def update_api(product:Product):
    products[19]=product
    return products[19]