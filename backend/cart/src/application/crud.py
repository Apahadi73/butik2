
from repo import models, schemas
from typing import Optional
from pymongo import MongoClient, ReturnDocument
from repo.models import transform_cart
from repo.schemas import Cart

#  create a new cart in the carts db
def create_cart(db: MongoClient, cart: Cart) -> Optional[dict]:
    carts = db.cart_db.carts
    temp = dict(cart)
    temp["_id"] = cart.id
    try:
        inserted_cart_id = carts.insert_one(temp).inserted_id
        found_cart = carts.find_one({"_id":inserted_cart_id})
        if found_cart:
            return dict(found_cart)
        else:
            return None
    except Exception as err:
        print(f"Unexpected {err=}, {type(err)=}")
        raise

#  fetches carts from the carts db
def get_carts(db: MongoClient, skip: int = 0, limit: int = 100):
    try:
        carts = db.cart_db.carts
        cart_list = carts.find({})
        if cart_list:
            return list(cart_list)
        else:
            return None
    except Exception as err:
        print(f"Unexpected {err=}, {type(err)=}")
        raise

#  fetches cart by id from the carts db
def get_cart_by_id(db: MongoClient, id:Optional[int]=None) -> Optional[dict]:
    try:
        carts = db.cart_db.carts
        found_cart = carts.find_one({"_id":id})
        if found_cart is not None:
            return dict(found_cart)
        else:
            return None
    except Exception as err:
        print(f"Unexpected {err=}, {type(err)=}")
        raise

#  update an existing cart in the carts db
def update_cart(db: MongoClient, cart: Cart, id:Optional[int]=None):
    carts = db.cart_db.carts
    temp = dict(cart)
    try:
        found_cart = carts.find_one_and_update({"_id":id},{"$set":temp},return_document=ReturnDocument.AFTER)
        print(found_cart)
        if found_cart:
            return dict(found_cart)
        else:
            return None
    except Exception as err:
        print(f"Unexpected {err=}, {type(err)=}")
        raise

#  deletes cart by id from the carts db
def delete_cart(db: MongoClient, id:Optional[int]=None):
    carts = db.cart_db.carts
    try:
        carts.delete_one({"_id":id})
        found_cart = carts.find_one({"_id":id})
        if not found_cart:
            return True
        else:
            return False
    except Exception as err:
        print(f"Unexpected {err=}, {type(err)=}")
        raise