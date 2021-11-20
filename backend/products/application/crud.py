from sqlalchemy.orm import Session
from repo import models, schemas

#  fetches products from the products db
def get_products(db: Session, skip: int = 0, limit: int = 100):
    return db.query(models.Product).offset(skip).limit(limit).all()

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

