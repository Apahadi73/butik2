import React, { useContext, useEffect, useState } from "react";
import { Text, TextType } from "../../../components/typography/Text.component";
import { StackNavigationProp } from "@react-navigation/stack";
import { RootTabParamList } from "../../../infrastructure/navigation/types";
import {
  CartList,
  CartScreenContainer,
  CheckoutButton,
} from "../components/Cart.styles";
import { CartContext } from "../repo/Cart.context";
import CartInfoCard from "../components/CartCard.component";
import {
  RowContainerSpacer,
  SpaceBetweenRow,
} from "../../../components/App.styles";
import { caculateOrderTotal, caculateTax } from "../utilities/utils.cart";
import { Item } from "react-native-paper/lib/typescript/components/List/List";
import { ProductModel } from "../../products/repo/models/ProductModel";
import { CartItemModel } from "../repo/models/CartItem";

export interface CartNavigatorProps {
  navigation: StackNavigationProp<RootTabParamList, "Cart">;
}

const CartScreen: React.FC<CartNavigatorProps> = ({ navigation }) => {
  const { cartItems, total } = useContext(CartContext);
  useEffect(() => {
    console.log("cartItems", cartItems.length);
  }, [cartItems]);

  return (
    <>
      <CartScreenContainer>
        <Text variant={TextType.header}>My Cart</Text>

        {cartItems && (
          <CartList<React.ElementType>
            data={cartItems}
            renderItem={({ item }: { item: CartItemModel }) => (
              <CartInfoCard cartItem={item} />
            )}
            keyExtractor={(item: CartItemModel) => item.id}
          />
        )}

        {cartItems.length > 0 ? (
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
            <CheckoutButton>Proceed to Checkout</CheckoutButton>
          </RowContainerSpacer>
        ) : (
          <></>
        )}
      </CartScreenContainer>
    </>
  );
};

export default CartScreen;
