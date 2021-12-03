import React, { FC } from "react";
import { SvgXml } from "react-native-svg";
import { StyleSheet, View, Image } from "react-native";

import { ProductModel } from "../repo/models/ProductModel";
import {
  ProductCard,
  Info,
  Section,
  SectionEnd,
  Rating,
  ProductCardCover,
  ProductImage,
} from "./Product-info-card.styles";
import star from "../../../assets/images/star";
import { Text, TextType } from "../../../components/typography/Text.component";
import { AppLogoContainer } from "../../../components/image/Image.component";
import {
  RowContainer,
  RowContainerSpacer,
  SpaceBetweenRow,
} from "../../../components/App.styles";
import { Spacer } from "../../../components/spacer/spacer.component";

interface ProductInfoCardProps {
  product: ProductModel;
}

const ProductInfoCard = ({ product }: ProductInfoCardProps): JSX.Element => {
  return (
    <ProductCard elevation={1}>
      <ProductImage source={{ uri: product.image }} />
      <Info>
        <Text variant={TextType.title}>{product.name}</Text>
        <Section>
          <RowContainerSpacer>
            <SpaceBetweenRow>
              <Rating>
                <SvgXml xml={star} width={20} height={20} />
                <Text variant={TextType.body}>{product.rating}</Text>
              </Rating>

              <Text variant={TextType.bodyBold}>{`$${product.price}`}</Text>
            </SpaceBetweenRow>
          </RowContainerSpacer>
          <SectionEnd></SectionEnd>
        </Section>
      </Info>
    </ProductCard>
  );
};
const styles = StyleSheet.create({
  logo: {
    width: 275,
    height: 150,
    resizeMode: "center",
  },
});

export default ProductInfoCard;
