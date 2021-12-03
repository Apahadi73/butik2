import React, { useContext, useEffect, useState } from "react";
import { AppContainer } from "../../../components/AppContainer.component";
import { Text, TextType } from "../../../components/typography/Text.component";
import { ProductsContext } from "../repo/Products.context";
import { StackNavigationProp } from "@react-navigation/stack";
import { AuthStackParamList } from "../../../infrastructure/navigation/types";
import { HomeScreenContainer } from "../components/Product-info-card.styles";
import ProductInfoCard from "../components/Product-info-card.components";

export interface LoginProps {
  navigation: StackNavigationProp<AuthStackParamList, "Login">;
}

const HomeScreen: React.FC<LoginProps> = ({ navigation }) => {
  const [paginationNum, setPaginationNum] = useState<number>(0);

  const { isLoading, error, products, fetchProducts } =
    useContext(ProductsContext);
  useEffect(() => {}, [navigation]);

  useEffect(() => {
    console.log("products:->>", products.length);
    console.log("products:->>", products[2]);
  }, [products]);

  return (
    <>
      <HomeScreenContainer>
        <Text variant={TextType.header}>Find the stuff you love</Text>
        {products &&
          products.map((product, index) => {
            return <ProductInfoCard product={product} key={index} />;
          })}
      </HomeScreenContainer>
    </>
  );
};

export default HomeScreen;
