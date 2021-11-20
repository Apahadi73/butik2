from sqlalchemy.sql.expression import true
from pydantic import BaseModel
from typing import Optional

class ProductBase(BaseModel):
    name: Optional[str] = None
    image: Optional[str] = None
    description:Optional[str] = None
    brand:Optional[str] = None
    category: Optional[str] = None
    price: int = 0
    count_in_stock: int = 0
    rating: int = 0
    num_reviews: int = 0
    
    
class ProductCreate(ProductBase):
    pass


class Product(ProductBase):
    id: Optional[int] = None
    
    # provide configurations to Pydantic.
    class Config:
        # Pydantic's orm_mode will tell the Pydantic model to read the data even if it is not a dict,
        # but an ORM model (or any other arbitrary object with attributes).
        orm_mode = True
        
