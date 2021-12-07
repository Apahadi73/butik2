import React, { useContext, useEffect, useState } from "react";
import { Text, TextType } from "../../../components/typography/Text.component";
import { StackNavigationProp } from "@react-navigation/stack";
import { RootTabParamList } from "../../../infrastructure/navigation/types";
import { CartScreenContainer, CheckoutButton } from "../components/Cart.styles";
import { CartContext } from "../repo/Cart.context";
import CartInfoCard from "../components/CartCard.component";
import {
  RowContainerSpacer,
  SpaceBetweenRow,
} from "../../../components/App.styles";
import { caculateOrderTotal, caculateTax } from "../utilities/utils.cart";

export interface CartNavigatorProps {
  navigation: StackNavigationProp<RootTabParamList, "Cart">;
}

const CartScreen: React.FC<CartNavigatorProps> = ({ navigation }) => {
  const { cartItems, total } = useContext(CartContext);
  useEffect(() => {
    console.log("cartItems", cartItems.length);
    console.log("cartItems Total", total);
  }, [cartItems]);

  return (
    <>
      <CartScreenContainer>
        <Text variant={TextType.header}>My Cart</Text>
        {cartItems &&
          cartItems.map((product) => (
            <CartInfoCard cartItem={product} key={product.id} />
          ))}
        <RowContainerSpacer>
          <SpaceBetweenRow>
            <Text variant={TextType.label}>Total before Tax</Text>
            <Text variant={TextType.body}>{total}</Text>
          </SpaceBetweenRow>
          <SpaceBetweenRow>
            <Text variant={TextType.label}>Tax</Text>
            <Text variant={TextType.body}>{caculateTax(total)}</Text>
          </SpaceBetweenRow>
          <SpaceBetweenRow>
            <Text variant={TextType.label}>Order Total</Text>
            <Text variant={TextType.body}>{caculateOrderTotal(total)}</Text>
          </SpaceBetweenRow>
        </RowContainerSpacer>
        <CheckoutButton>Proceed to Checkout</CheckoutButton>
      </CartScreenContainer>
    </>
  );
};

export default CartScreen;
