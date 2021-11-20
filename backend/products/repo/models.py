from sqlalchemy import Boolean, Column, ForeignKey, Integer, String
from sqlalchemy.orm import relationship
from .database import Base

class Product(Base):
    __tablename__ = "productss"
    id = Column(Integer, primary_key=True, index=True)
    name = Column(String)
    image = Column(String)
    description = Column(String)
    brand = Column(String)
    category = Column(String)
    price = Column(Integer)
    count_in_stock = Column(Integer)
    rating = Column(Integer)
    num_reviews = Column(Integer)
    
    def __str__(self) -> str:
        return "Product\nid="+str(self.id)+"\nname="+self.name+"\nimage="+self.image+"\ndescription="+self.description+"\nbrand="+self.brand

