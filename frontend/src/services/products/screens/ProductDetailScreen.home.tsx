import React, { useContext, useEffect } from "react";
import { Text, TextType } from "../../../components/typography/Text.component";
import { ProductsContext } from "../repo/Products.context";
import { StackNavigationProp } from "@react-navigation/stack";
import { HomeStackParamList } from "../../../infrastructure/navigation/types";
import {
  HomeScreenContainer,
  ProductImage,
  Rating,
} from "../components/Product-info-card.styles";
import { RouteProp } from "@react-navigation/native";
import {
  MarginType,
  SizeEnum,
  Spacer,
} from "../../../components/spacer/spacer.component";
import { View } from "../../../components_r/Themed";
import {
  Button,
  RowContainerSpacer,
  SpaceBetweenRow,
} from "../../../components/App.styles";
import { SvgXml } from "react-native-svg";
import star from "../../../assets/images/star";
import { AddButton } from "../components/Product-detail.styles";

interface ProductDetailScreenNavigatorProps {
  navigation: StackNavigationProp<HomeStackParamList, "ProductDetailScreen">;
  route: RouteProp<HomeStackParamList, "ProductDetailScreen">;
}

const ProductDetailsScreen: React.FC<ProductDetailScreenNavigatorProps> = ({
  navigation,
  route,
}) => {
  const { isLoading, error, product, fetchProductById } =
    useContext(ProductsContext);
  useEffect(() => {
    console.log(route.params.id);
    fetchProductById(route.params.id ? route.params.id : 1);
  }, [navigation]);

  // useEffect(() => {
  //   console.log("product->", product);
  // }, [product]);

  return (
    <>
      <HomeScreenContainer>
        {product && (
          <>
            <Text variant={TextType.title}>{product.name}</Text>
            <ProductImage source={{ uri: product.image }} />
            <Text variant={TextType.body}>{product.description}</Text>
            <RowContainerSpacer>
              <SpaceBetweenRow>
                <Rating>
                  <SvgXml xml={star} width={20} height={20} />
                  <Text variant={TextType.body}>{product.rating}</Text>
                </Rating>
                <Text variant={TextType.bodyBold}>{`$${product.price}`}</Text>
              </SpaceBetweenRow>
            </RowContainerSpacer>

            <AddButton onPress={() => {}}>Add to Cart</AddButton>
          </>
        )}
      </HomeScreenContainer>
    </>
  );
};

export default ProductDetailsScreen;
