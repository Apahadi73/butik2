from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
import config

SQLALCHEMY_DATABASE_URL = "postgresql://postgres:password@localhost:5432"

engine = create_engine(SQLALCHEMY_DATABASE_URL)

# Each instance of the SessionLocal class will be a database session. The class itself is not a database session yet.
# But once we create an instance of the SessionLocal class, this instance will be the actual database session.
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base = declarative_base()
