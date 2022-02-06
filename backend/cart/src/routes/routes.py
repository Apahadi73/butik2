from fastapi import  Path, APIRouter,Depends, HTTPException
from typing import Any, List
from pymongo import MongoClient

import repo.schemas as schemas
import repo.database as db
import application.crud as crud

# creates router
router = APIRouter(
    prefix="/api/v1",
    tags=["v1"],
    responses={404: {"description": "Not found"}},
)

@router.get("/cart")
def index():
    return "Welcome to the cart service v1"

@router.get("/cart/list", response_model=List[schemas.Cart])
def get_carts(skip: int = 0, limit: int = 100, db: MongoClient = Depends(db.connect_to_db)):
    try:
        carts = crud.get_carts(db, skip=skip, limit=limit)
        if not carts:
            raise HTTPException(status_code=404, detail="carts not found")
        return carts

    except Exception as err:
        print(f"Unexpected {err=}, {type(err)=}")
        raise HTTPException(status_code=500, detail="Something went wrong while fetching carts.")
        
   

@router.post("/cart", response_model=schemas.Cart)
def create_cart(
    cart: schemas.CartCreate,
    db: MongoClient = Depends(db.connect_to_db)
):
    try:
        response =  crud.create_cart(db=db, cart=cart)
        print(response)
        return response
    except Exception as err:
        print(f"Unexpected {err=}, {type(err)=}")
        raise HTTPException(status_code=500, detail="Something went wrong while initializing cart.")
        

@router.get("/cart/{id}")
def get_carts_by_id(id:str = Path(None,description="Enter Cart Id"), db: MongoClient = Depends(db.connect_to_db)):
    try:
        cart = crud.get_cart_by_id(db=db,id=id)
        if cart is None:
            raise HTTPException(status_code=404, detail="Cart not found")
        return cart
    except HTTPException:
        raise HTTPException(status_code=404, detail="Cart not found")
    except Exception as err:
        print(f"Unexpected {err=}, {type(err)=}")
        raise HTTPException(status_code=500, detail="Something went wrong while fetching cart information.")


@router.put("/cart/{id}")
def update_cart(cart: schemas.CartUpdate, 
                   db: MongoClient = Depends(db.connect_to_db),
                   id:str = Path(None,description="Enter Cart Id")):
    try:
        cart = crud.update_cart(db,cart,id)
        if not cart:
            raise HTTPException(status_code=404, detail="Cart not found")
        return cart
    except HTTPException:
        raise HTTPException(status_code=404, detail="Cart not found")
    except Exception as err:
        print(f"Unexpected {err=}, {type(err)=}")
        raise HTTPException(status_code=500, detail="Something went wrong while updating cart.")

@router.delete("/cart/{id}")
def delete_carts_by_id(id:str = Path(None,description="Enter Cart Id"), db: MongoClient = Depends(db.connect_to_db)):
    try:
        isDeleted = crud.delete_cart(db,id)
        if isDeleted:
            return "Successfully deleted cart with id: ${id}"
        else:
            raise HTTPException(status_code=404, detail="Cart not found")
    except HTTPException:
        raise HTTPException(status_code=404, detail="Cart not found")
    except Exception as err:
        print(f"Unexpected {err=}, {type(err)=}")
        raise HTTPException(status_code=500, detail="Something went wrong while deleting cart.")

