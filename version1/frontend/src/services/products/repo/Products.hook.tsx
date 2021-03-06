import { useCallback, useEffect, useState } from "react";
import aAxios from "../utilities/productAxios";
import jwt from "react-native-pure-jwt";
import PersistentStorage, {
  PSKeyEnum,
} from "../../persistent_storage/SecureStore";
import productAxios from "../utilities/productAxios";
import { ProductModel } from "./models/ProductModel";
import { ProductConstants } from "../utilities/CONSTANTS";

const useProductsHook = () => {
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [products, setProducts] = useState<ProductModel[]>([]);
  const [product, setProduct] = useState<ProductModel | null>(null);
  const [error, setError] = useState<string>("");

  useEffect(() => {
    fetchProducts(0, ProductConstants.PRODUCT_LIMIT);
  }, []);

  const fetchProducts = async (pagNum: number = 0, limit: number) => {
    try {
      setIsLoading(true);
      const response = await productAxios.get(
        `list?skip=${pagNum}&limit=${limit}`
      );

      if (response && response.data) {
        const moreProducts = response.data;
        let updatedProducts: ProductModel[] = products;
        updatedProducts.push(...moreProducts);
        console.log("fetched products");
        setProducts(updatedProducts);
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

  const fetchProductById = async (id: number) => {
    try {
      setIsLoading(true);
      const response = await productAxios.get(`/${id}`);

      if (response && response.data) {
        const rProduct = response.data;
        setProduct(rProduct);
        setError("");
        setIsLoading(true);
      }
    } catch (e: any) {
      let errMessage = "Something went wrong";
      if (e.response && e.response.data) {
        errMessage = e.response.data;
      }
      setError(errMessage);
      setIsLoading(true);
    }
  };

  return {
    isLoading,
    error,
    products,
    product,
    fetchProducts,
    fetchProductById,
  };
};

export default useProductsHook;
