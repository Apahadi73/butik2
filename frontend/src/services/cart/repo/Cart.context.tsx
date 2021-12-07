import React, { createContext, FC } from "react";
import { ProductModel } from "./models/CartItem";
import useAuthHook from "./Cart.hook";
import useCartHook from "./Cart.hook";

// our custom Cart context
export const CartContext = createContext<{
  isLoading: boolean;
  error: string;
  cartItems: ProductModel[];
  total: number;
  addItemToCart: (product: ProductModel) => void;
  IncreaseIteminCart: (id: number) => void;
  DecreaseIteminCart: (id: number) => void;
}>({
  isLoading: true,
  error: "",
  cartItems: [],
  total: 0,
  addItemToCart: (product: ProductModel) => {},
  IncreaseIteminCart: (id: number) => {},
  DecreaseIteminCart: (id: number) => {},
});

interface Props {
  // any props that come into the component
}

// our Cart context provider object
const CartContextProvider: FC<Props> = ({ children }) => {
  const {
    isLoading,
    error,
    cartItems,
    total,
    addItemToCart,
    IncreaseIteminCart,
    DecreaseIteminCart,
  } = useCartHook();

  return (
    <CartContext.Provider
      value={{
        isLoading: isLoading,
        error: error,
        cartItems: cartItems,
        total: total,
        addItemToCart: addItemToCart,
        IncreaseIteminCart: IncreaseIteminCart,
        DecreaseIteminCart: DecreaseIteminCart,
      }}
    >
      {children}
    </CartContext.Provider>
  );
};

export default CartContextProvider;
