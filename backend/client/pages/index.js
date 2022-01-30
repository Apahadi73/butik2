import axios from "axios";
import Head from "next/head";
import Image from "next/image";
import { useEffect } from "react";
import { Col, Row } from "react-bootstrap";
import buildClient from "../api/build-client";
import Loader from "../components/Loader";
import Message from "../components/Message";
import Meta from "../components/Meta";
import Product from "../components/Product";

const Home = ({ products, error }) => {
  let loading = false;

  return (
    <div>
      <Head>
        <title>Butik</title>
        <meta name="keywords" content="shopping products" />
      </Head>
      <>
        <Meta />
        {loading ? (
          <Loader />
        ) : error ? (
          <Message variant="danger">{error}</Message>
        ) : (
          <>
            <Row>
              {products &&
                products.map((product) => (
                  <Col key={product.id} sm={12} md={6} lg={4} xl={3}>
                    <Product product={product} />
                  </Col>
                ))}
            </Row>
          </>
        )}
      </>
    </div>
  );
};

Home.getInitialProps = async (context, client) => {
  let products;
  let error;
  try {
    let { data } = await client.get("/api/v1/products/list");
    products = data == null ? [] : data;
  } catch (err) {
    error = err;
  }
  return { products, error };
};

export default Home;
