import React, { useContext, useEffect, useState } from "react";
import { StyleSheet, Image } from "react-native";
import images from "../../../assets/images";
import { AppContainer } from "../../../components/AppContainer.component";
import { AppLogoContainer } from "../../../components/image/Image.component";
import { Text, TextType } from "../../../components/typography/Text.component";
import {
  AccountContainer,
  AuthButton,
  AuthInput,
  AuthLink,
  ErrorContainer,
} from "../components/Authentication.components";
import { ProductsContext } from "../repo/Products.context";
import { StackNavigationProp } from "@react-navigation/stack";
import { AuthStackParamList } from "../../../infrastructure/navigation/types";

export interface LoginProps {
  navigation: StackNavigationProp<AuthStackParamList, "Login">;
}

// Constants
const LIMIT = 10;

const HomeScreen: React.FC<LoginProps> = ({ navigation }) => {
  const [paginationNum, setPaginationNum] = useState<number>(0);

  const { isLoading, error, products, fetchProducts } =
    useContext(ProductsContext);
  useEffect(() => {
    fetchProducts(0, LIMIT);
  }, [navigation]);

  useEffect(() => {
    console.log("products:->>", products.length);
    console.log("products:->>", products[2]);
  }, [products]);

  return (
    <>
      <AppContainer>
        <AccountContainer>
          <Text variant={TextType.body}>Home Screen1</Text>
        </AccountContainer>
      </AppContainer>
    </>
  );
};

export default HomeScreen;
