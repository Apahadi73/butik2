import React, { createContext, FC } from "react";
import { ProductModel } from "./models/ProductModel";
import useProductsHook from "./Products.hook";
import useAuthHook from "./Products.hook";

// our custom Products context
export const ProductsContext = createContext<{
  isLoading: boolean;
  error: string;
  products: ProductModel[];
  product: ProductModel | null;
  fetchProducts: (pagNum: number, limit: number) => Promise<void>;
  fetchProductById: (id: number) => Promise<void>;
}>({
  isLoading: true,
  error: "",
  products: [],
  product: null,
  fetchProducts: async (pagNum: number, limit: number) => {},
  fetchProductById: async (id: number) => {},
});

interface Props {
  // any props that come into the component
}

// our Products context provider object
const ProductsContextProvider: FC<Props> = ({ children }) => {
  const {
    isLoading,
    error,
    products,
    product,
    fetchProducts,
    fetchProductById,
  } = useProductsHook();

  return (
    <ProductsContext.Provider
      value={{
        isLoading: isLoading,
        error: error,
        products: products,
        fetchProducts: fetchProducts,
        product: product,
        fetchProductById: fetchProductById,
      }}
    >
      {children}
    </ProductsContext.Provider>
  );
};

export default ProductsContextProvider;
