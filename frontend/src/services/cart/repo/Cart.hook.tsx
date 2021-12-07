import { useCallback, useEffect, useState } from "react";
import aAxios from "../utilities/productAxios";
import jwt from "react-native-pure-jwt";
import PersistentStorage, {
  PSKeyEnum,
} from "../../persistent_storage/SecureStore";
import productAxios from "../utilities/productAxios";
import { CartItemModel } from "./models/CartItem";
import { ProductConstants } from "../utilities/CONSTANTS";

const useCartHook = () => {
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [cartItems, setCartItems] = useState<CartItemModel[]>([]);
  const [error, setError] = useState<string>("");
  const [total, setTotal] = useState<number>(0);

  const addItemToCart = (product: CartItemModel) => {
    const updatedTotal = product.price ? total + product.price : total;
    setTotal(updatedTotal);
    const updatedCart = [...cartItems, product];
    setCartItems(updatedCart);
  };

  const IncreaseIteminCart = (id: number) => {
    const updatedCart = cartItems.map((item) => {
      if (item.id === id) {
        item.number += 1;
      }
      return item;
    });
    setCartItems(updatedCart);
  };

  const DecreaseIteminCart = (id: number) => {
    const updatedCart = cartItems.map((item) => {
      if (item.id === id && item.number > 0) {
        item.number -= 1;
      }
      return item;
    });
    setCartItems(updatedCart);
  };
  return {
    isLoading,
    error,
    cartItems,
    total,
    addItemToCart,
    IncreaseIteminCart,
    DecreaseIteminCart,
  };
};

export default useCartHook;
