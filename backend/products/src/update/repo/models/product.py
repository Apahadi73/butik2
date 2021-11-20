from pydantic import BaseModel
from typing import Optional

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