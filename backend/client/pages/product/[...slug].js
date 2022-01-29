import { useRouter } from "next/router";

const ProductPage = () => {
  return (
    <>
      <p>No such product found</p>
    </>
  );
};

export async function getStaticPaths() {
  // Return a list of possible value for id
}

export async function getStaticProps({ params }) {
  // Fetch necessary data for the blog post using params.id

  return {};
}

export default ProductPage;
