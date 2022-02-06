from starlette.config import Config
from starlette.datastructures import Secret

config = Config(".env")

PROJECT_NAME = "Butik"
VERSION = "1.0.0"
API_PREFIX = "/api/cart"

SECRET_KEY = config("SECRET_KEY", cast=Secret, default="CHANGEME")

DATABASE_HOST = config("DATABASE_HOST", cast=Secret,default="localhost")
DATABASE_SERVER = config("DATABASE_SERVER", cast=str, default="mongodb")
DATABASE_NAME = config("DATABASE_NAME", cast=str, default="localhost")
DATABASE_PORT = config("DATABASE_PORT", cast=str, default="27020")
DATABASE_DB = config("DATABASE_DB", cast=str, default="cart")

DATABASE_URL = config(
  "DATABASE_URL",
  default=f"{DATABASE_SERVER}://{DATABASE_HOST}:{DATABASE_PORT}/{DATABASE_DB}"
)

# DATABASE_URL = "mongodb://localhost:27020/cart"

