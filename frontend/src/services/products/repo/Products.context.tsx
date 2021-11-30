import React, { createContext, FC } from "react";
import { ProductModel } from "./models/ProductModel";
import useProductsHook from "./Products.hook";
import useAuthHook from "./Products.hook";

// our custom Products context
export const ProductsContext = createContext<{
  isLoading: boolean;
  error: string;
  products: ProductModel[];
  fetchProducts: () => Promise<void>;
}>({
  isLoading: true,
  error: "",
  products: [],
  fetchProducts: async () => {},
});

interface Props {
  // any props that come into the component
}

// our Products context provider object
const ProductsContextProvider: FC<Props> = ({ children }) => {
  const { isLoading, error, products, fetchProducts } = useProductsHook();

  return (
    <ProductsContext.Provider
      value={{
        isLoading: isLoading,
        error: error,
        products: products,
        fetchProducts: fetchProducts,
      }}
    >
      {children}
    </ProductsContext.Provider>
  );
};

export default ProductsContextProvider;
