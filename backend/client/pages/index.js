import axios from "axios";
import Head from "next/head";
import Image from "next/image";
import { Col, Row } from "react-bootstrap";
import Loader from "../components/Loader";
import Message from "../components/Message";
import Meta from "../components/Meta";

const Home = (data) => {
  const loading = true;
  return (
    <div>
      <Head>
        <title>Butik</title>
        <meta name="keywords" content="shopping products" />
      </Head>
      <>
        <Meta />
        {/* {!keyword ? (
          <ProductCarousel />
        ) : (
          <Link to="/" className="btn btn-light">
            Go Back
          </Link>
        )} */}
        {/* {loading ? (
          <Loader />
        ) : error ? (
          <Message variant="danger">{error}</Message>
        ) : (
          <>
            <p>Amir</p>
          </>
        )} */}
        <div>Amir123</div>
      </>
    </div>
  );
};

Home.getInitialProps = async (context, client) => {
  let { data } = await client.get("/api/v1/products/list");
  console.log(data);
  return data;
};

export default Home;
