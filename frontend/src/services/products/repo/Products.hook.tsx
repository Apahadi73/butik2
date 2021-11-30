import { useCallback, useEffect, useState } from "react";
import aAxios from "../utilities/productAxios";
import jwt from "react-native-pure-jwt";
import PersistentStorage, {
  PSKeyEnum,
} from "../../persistent_storage/SecureStore";
import productAxios from "../utilities/productAxios";
import { ProductModel } from "./models/ProductModel";

const useProductsHook = () => {
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [products, setProducts] = useState<ProductModel[]>([]);
  const [error, setError] = useState<string>("");
  const [paginationNum, setPaginationNum] = useState<number[]>([0, 10]);

  const fetchProducts = async () => {
    try {
      setIsLoading(true);
      const response = await productAxios.get(
        `list?skip=${paginationNum[0]}&limit=${paginationNum[1]}`
      );

      if (response && response.data) {
        const moreProducts = response.data;
        let updatedProducts: ProductModel[] = products;
        updatedProducts.push(...moreProducts);
        setProducts(updatedProducts);
        setPaginationNum([
          paginationNum[0] + paginationNum[1],
          paginationNum[1],
        ]);
        setError("");
        setIsLoading(true);
      }
    } catch (e: any) {
      let errMessage = "Something went wrong";
      // error handling
      if (e.response && e.response.data) {
        errMessage = e.response.data;
      }
      setError(errMessage);
      setIsLoading(true);
    }
  };

  return { isLoading, error, products, fetchProducts };
};

export default useProductsHook;
