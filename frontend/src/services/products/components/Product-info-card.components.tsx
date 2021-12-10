import React from "react";
import { SvgXml } from "react-native-svg";

import { ProductModel } from "../repo/models/ProductModel";
import {
  ProductCard,
  Info,
  Section,
  Rating,
  ProductCardCover,
  ProductImage,
} from "./Product-info-card.styles";
import star from "../../../assets/images/star";
import { Text, TextType } from "../../../components/typography/Text.component";
import {
  RowContainerSpacer,
  SpaceBetweenRow,
} from "../../../components/App.styles";
import { StackNavigationProp } from "@react-navigation/stack";
import { HomeStackParamList } from "../../../infrastructure/navigation/types";

interface ProductInfoCardProps {
  product: ProductModel;
  navigation: StackNavigationProp<HomeStackParamList, "HomeScreen">;
}

const ProductInfoCard = ({
  product,
  navigation,
}: ProductInfoCardProps): JSX.Element => {
  const onCardPressedHandler = () => {
    navigation.navigate("ProductDetailScreen", {
      id: product.id,
    });
  };
  console.log(product);
  return (
    <ProductCardCover onPress={onCardPressedHandler}>
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
          </Section>
        </Info>
      </ProductCard>
    </ProductCardCover>
  );
};

export default ProductInfoCard;
