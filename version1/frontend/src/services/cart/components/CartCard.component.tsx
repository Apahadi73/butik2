import React, { useContext, useEffect } from "react";

import { CartItemModel } from "../repo/models/CartItem";

import { Text, TextType } from "../../../components/typography/Text.component";
import {
  RowContainerSpacer,
  SpaceBetweenRow,
} from "../../../components/App.styles";
import { CartCard, ProductThumbnail } from "./Cart.styles";
import { themeColor } from "../../../infrastructure/themes/ThemeColor.themes";
import FAIcon from "../../../components/FontAwesomeIcon.component";
import { CartContext } from "../repo/Cart.context";

interface CartCardProps {
  cartItem: CartItemModel;
}

const CartInfoCard = ({ cartItem }: CartCardProps): JSX.Element => {
  const { addItemToCart, DecreaseIteminCart, cartItems } =
    useContext(CartContext);

  return (
    <CartCard elevation={1}>
      <RowContainerSpacer>
        <SpaceBetweenRow>
          <ProductThumbnail source={{ uri: cartItem.image }} />
          <Text
            variant={TextType.body}
            ellipsizeMode="middle"
            style={{ width: 100 }}
          >
            {cartItem.name}
          </Text>
          <FAIcon name="plus" color={themeColor.ui.primary} />
          <Text variant={TextType.body}>
            {cartItem.number ? cartItem.number : 1}
          </Text>
          <FAIcon name="minus" color={themeColor.ui.error} />
          <Text variant={TextType.bodyBold}>{`$${cartItem.price}`}</Text>
        </SpaceBetweenRow>
      </RowContainerSpacer>
    </CartCard>
  );
};

export default CartInfoCard;
