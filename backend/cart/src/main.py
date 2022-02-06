from fastapi import FastAPI
import routes.routes as r
import uvicorn
from utilities.seeder import seed_products
# create our application
app = FastAPI(title="Cart Service")

# sets up router
app.include_router(r.router)

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=5000)