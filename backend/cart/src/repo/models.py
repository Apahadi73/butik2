def transform_cart(cart,cid)->dict:
    return {
        "cid":str(cid),
        "id": str(cart["id"]),
        "items": dict(cart["items"]),
        "total": float(cart["total"]),
    }
