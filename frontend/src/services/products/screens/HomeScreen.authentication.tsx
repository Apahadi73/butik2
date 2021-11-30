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
import {
  AuthenticationContext,
  ProductsContext,
} from "../repo/Products.context";
import { StackNavigationProp } from "@react-navigation/stack";
import { AuthStackParamList } from "../../../infrastructure/navigation/types";

export interface LoginProps {
  navigation: StackNavigationProp<AuthStackParamList, "Login">;
}
const HomeScreen: React.FC<LoginProps> = ({ navigation }) => {
  const { isLoading, error, products, fetchProducts } =
    useContext(ProductsContext);
  useEffect(() => {
    fetchProducts();
  }, [console.log(navigation)]);

  useEffect(() => {
    console.log(products);
  }, [products]);

  return (
    <>
      <AppContainer>
        <AccountContainer>
          <Text variant={TextType.body}>Home Screen</Text>
        </AccountContainer>
      </AppContainer>
    </>
  );
};

export default HomeScreen;
