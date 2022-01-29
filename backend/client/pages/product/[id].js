import { useRouter } from "next/router";

const ProductPage = () => {
  return (
    <>
      <p>I am product page</p>
    </>
  );
};

export async function getStaticPaths(context, client) {
  // Return a list of possible value for id
  let products;
  let paths = [];
  try {
    let { data } = await client.get("/api/v1/products/list");
    products = data == null ? [] : data;
    if (products) {
      paths = products.map((product) => {
        params: {
          id: product.id.toString();
        }
      });
    }
  } catch (err) {
    console.log(err);
  }

  return { paths, fallback: false };
}

export async function getStaticProps(context) {
  // Fetch necessary data for the blog post using params.id
  const { params } = context;
  const pId = params.id;
  console.log(pId);

  return {};
}

export default ProductPage;
