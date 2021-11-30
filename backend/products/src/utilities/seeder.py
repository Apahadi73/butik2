from repo import models
from repo.dummy_products import dummy_products
import repo.database as repo_db

def seed_products():
    for product in dummy_products:
        db = repo_db.SessionLocal()
        # step 1: Create a SQLAlchemy model instance with your data.
        db_product = models.Product(**product)
        # step 2: add that instance object to your database session.
        db.add(db_product)
        # step 3: commit the changes to the database (so that they are saved).
        db.commit()
        # step 4: refresh your instance (so that it contains any new data from the database, like the generated ID).
        db.refresh(db_product)
        db.close()
    