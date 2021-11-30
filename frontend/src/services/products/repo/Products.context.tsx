import React, { createContext, FC } from "react";
import useProductsHook from "./Products.hook";
import useAuthHook from "./Products.hook";

// our custom Products context
export const ProductsContext = createContext({
  isLoading: true,
  error: "",
  fetchProducts: async () => {},
});

interface Props {
  // any props that come into the component
}

// our Products context provider object
const ProductsContextProvider: FC<Props> = ({ children }) => {
  const { isLoading, error, fetchProducts } = useProductsHook();

  return (
    <ProductsContext.Provider
      value={{
        isLoading: isLoading,
        error: error,
        fetchProducts: fetchProducts,
      }}
    >
      {children}
    </ProductsContext.Provider>
  );
};

export default ProductsContextProvider;
