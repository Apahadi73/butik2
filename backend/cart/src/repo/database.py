import config
from pymongo import MongoClient

# creates a new mongodb client
def connect_to_db():
    try:
        print(config.DATABASE_URL)
        conn = MongoClient(config.DATABASE_URL)
        print(f"Successfully to the mongodb database")
        return conn
    except BaseException as err:
        print(f"Error connecting to the mongodb database")
        print(f"Unexpected {err=}, {type(err)=}")
        raise
    return None

db_conn = connect_to_db()