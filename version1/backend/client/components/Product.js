import React from "react";
import { Card } from "react-bootstrap";
import Rating from "./Rating";
import Link from "next/link";

const Product = ({ product }) => {
  return (
    <Card className="my-3 p-3 rounded">
      <Link href={`/product/${product.id}`}>
        <Card.Img src={product.image} variant="top" />
      </Link>

      <Card.Body>
        <Link href={`/product/${product.id}`}>
          <Card.Title as="div">
            <strong>{product.name}</strong>
          </Card.Title>
        </Link>

        <Card.Text as="div">
          <Rating
            value={product.rating}
            text={`${product.num_reviews} reviews`}
          />
        </Card.Text>

        <Card.Text as="h3">${product.price}</Card.Text>
      </Card.Body>
    </Card>
  );
};

export default Product;
