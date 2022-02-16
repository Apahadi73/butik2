import config
from pymongo import MongoClient

# creates a new mongodb client
def connect_to_db():
    try:
        conn = MongoClient(config.DATABASE_URL)
        print(f"Successfully connected to the database on:{config.DATABASE_URL}")
        return conn
    except BaseException as err:
        print(f"Error connecting to the mongodb database")
        print(f"Unexpected {err=}, {type(err)=}")
        raise
    return None

db_conn = connect_to_db()