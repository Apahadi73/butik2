import React, { useContext, useEffect, useState } from "react";
import { AppContainer } from "../../../components/AppContainer.component";
import { Text, TextType } from "../../../components/typography/Text.component";
import { ProductsContext } from "../repo/Products.context";
import { StackNavigationProp } from "@react-navigation/stack";
import {
  AuthStackParamList,
  HomeStackParamList,
} from "../../../infrastructure/navigation/types";
import { HomeScreenContainer } from "../components/Product-info-card.styles";
import ProductInfoCard from "../components/Product-info-card.components";

export interface HomeNavigatorProps {
  navigation: StackNavigationProp<HomeStackParamList, "HomeScreen">;
}

const HomeScreen: React.FC<HomeNavigatorProps> = ({ navigation }) => {
  const [paginationNum, setPaginationNum] = useState<number>(0);

  const { isLoading, error, products, fetchProducts } =
    useContext(ProductsContext);
  useEffect(() => {}, [navigation]);
  return (
    <>
      <HomeScreenContainer>
        <Text variant={TextType.header}>Find the stuff you love</Text>
        {products &&
          products.map((product, index) => {
            return (
              <ProductInfoCard
                product={product}
                key={index}
                navigation={navigation}
              />
            );
          })}
      </HomeScreenContainer>
    </>
  );
};

export default HomeScreen;
