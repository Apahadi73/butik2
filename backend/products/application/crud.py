from sqlalchemy.orm import Session
from repo import models, schemas
from typing import Optional

#  create a new product in the products db
def create_product(db: Session, product: schemas.Product):
    # step 1: Create a SQLAlchemy model instance with your data.
    db_product = models.Product(**product.dict())
    # step 2: add that instance object to your database session.
    db.add(db_product)
    # step 3: commit the changes to the database (so that they are saved).
    db.commit()
    # step 4: refresh your instance (so that it contains any new data from the database, like the generated ID).
    db.refresh(db_product)
    return db_product

#  fetches products from the products db
def get_products(db: Session, skip: int = 0, limit: int = 100):
    return db.query(models.Product).offset(skip).limit(limit).all()

#  fetches product by id from the products db
def get_product_by_id(db: Session, id:Optional[int]=None):
    res =  db.query(models.Product).filter(models.Product.id == id).first()
    return res

#  update an existing product in the products db
def update_product(db: Session, product: schemas.Product, id:Optional[int]=None):
    """
    Using a new update method seen in FastAPI https://github.com/tiangolo/fastapi/pull/2665
    Simple, does not need each attribute to be updated individually
    Uses python in built functionality... preferred to the pydintic related method
    """
    # get the existing product
    db_product = db.query(models.Product).filter(models.Product.id == id).one_or_none()
    
    if db_product is None:
        return None
    
    for attr,val in vars(product).items():
        if val:
            setattr(db_product,attr,val)
    db.add(db_product)
    db.commit()
    db.refresh(db_product)
    return db_product

#  deletes product by id from the products db
def delete_product(db: Session, id:Optional[int]=None):
    # get the existing product
    db_product = db.query(models.Product).filter(models.Product.id == id).one_or_none()
    
    if db_product is None:
        return None
    
    db.delete(db_product)
    db.commit()
    return {"ok": True}