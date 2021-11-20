from sqlalchemy import Boolean, Column, ForeignKey, Integer, String
from sqlalchemy.orm import relationship
from .database import Base

class Product(Base):
    __tablename__ = "products"
    id = Column(Integer, primary_key=True, index=True)
    name = Column(String, unique=True, index=True)
    image = Column(String, unique=True, index=True)
    description = Column(String, unique=True, index=True)
    brand = Column(String, unique=True, index=True)
    category = Column(String, unique=True, index=True)
    price = Column(Integer, unique=True, index=True)
    count_in_stock = Column(Integer, unique=True, index=True)
    rating = Column(Integer, unique=True, index=True)
    num_reviews = Column(Integer, unique=True, index=True)

