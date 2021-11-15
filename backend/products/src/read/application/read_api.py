from src.dummy_data.products import products

async def get_products_api():
    return products
    
async def get_products_by_id_api(id:int):
    return products[id]

async def get_products_by_name_api(name:str):
    for product_id in products:
        if products[product_id]["name"] == name:
            return  products[product_id]
    return {"Data":"Not Found"}
