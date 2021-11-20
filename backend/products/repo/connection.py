import repo.database as repo_db
import repo.models as models

models.Base.metadata.create_all(bind=repo_db.engine)

# We need to have an independent database session/connection (SessionLocal) per request, 
# use the same session through all the request and then close it after the request is finished.
# And then a new session will be created for the next request.
# Dependency
def get_db():
    db = repo_db.SessionLocal()
    try:
        yield db
    finally:
        db.close()