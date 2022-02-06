from ast import List
from pydantic import BaseModel
from typing import Optional


# ----------------------------Cart Item Model---------------------------------
class CartItemBase(BaseModel):
    id:str = 0
    name: Optional[str] = None
    quantity:int = 0
    price:float = 0
    image: Optional[str] = None
    
class CartItemCreate(CartItemBase):
    pass

class CartItemUpdate(CartItemBase):
    pass

class CartItem(CartItemBase):
    # provide configurations to Pydantic.
    class Config:
        # Pydantic's orm_mode will tell the Pydantic model to read the data even if it is not a dict,
        # but an ORM model (or any other arbitrary object with attributes).
        orm_mode = True

# ----------------------------Cart Model---------------------------------
class CartBase(BaseModel):
    items: dict = dict()
    total:float = 0.0
    
class CartCreate(CartBase):
    id:Optional[str] = None
    pass

class CartUpdate(CartBase):
    pass

class Cart(CartBase):
    # provide configurations to Pydantic.
    class Config:
        # Pydantic's orm_mode will tell the Pydantic model to read the data even if it is not a dict,
        # but an ORM model (or any other arbitrary object with attributes).
        orm_mode = True
        
