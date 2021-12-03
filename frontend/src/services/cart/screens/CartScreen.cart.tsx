import React, { useContext, useEffect, useState } from "react";
import { Text, TextType } from "../../../components/typography/Text.component";
import { StackNavigationProp } from "@react-navigation/stack";
import { RootTabParamList } from "../../../infrastructure/navigation/types";
import { CartScreenContainer } from "../components/Cart.styles";
import { CartContext } from "../repo/Cart.context";
import CartInfoCard from "../components/CartCard.component";

export interface CartNavigatorProps {
  navigation: StackNavigationProp<RootTabParamList, "Cart">;
}

const CartScreen: React.FC<CartNavigatorProps> = ({ navigation }) => {
  const { cartItems } = useContext(CartContext);
  useEffect(() => {
    console.log("cartItems", cartItems.length);
  }, [cartItems]);
  return (
    <>
      <CartScreenContainer>
        <Text variant={TextType.header}>My Cart</Text>
        {cartItems &&
          cartItems.map((product) => (
            <CartInfoCard cartItem={product} key={product.id} />
          ))}
      </CartScreenContainer>
    </>
  );
};

export default CartScreen;
