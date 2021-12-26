import React, { useContext, useEffect, useState } from "react";
import { AppContainer } from "../../../components/AppContainer.component";
import { Text, TextType } from "../../../components/typography/Text.component";
import { ProductsContext } from "../repo/Products.context";
import { StackNavigationProp } from "@react-navigation/stack";
import {
  AuthStackParamList,
  HomeStackParamList,
} from "../../../infrastructure/navigation/types";
import {
  HomeScreenContainer,
  ProductList,
} from "../components/Product-info-card.styles";
import ProductInfoCard from "../components/Product-info-card.components";
import { AuthButton } from "../../authentication/components/Authentication.components";
import { ProductModel } from "../repo/models/ProductModel";

export interface HomeNavigatorProps {
  navigation: StackNavigationProp<HomeStackParamList, "HomeScreen">;
}

const HomeScreen: React.FC<HomeNavigatorProps> = ({ navigation }) => {
  const [paginationNum, setPaginationNum] = useState<number>(0);

  const { isLoading, error, products, fetchProducts } =
    useContext(ProductsContext);
  useEffect(() => {
    console.log("products->", products.length);
  }, [navigation]);

  return (
    <>
      <HomeScreenContainer>
        <Text variant={TextType.header}>Find the stuff you love</Text>
        {products && (
          // products.map((product, index) => {
          //   return (
          //     <ProductInfoCard
          //       product={product}
          //       key={index}
          //       navigation={navigation}
          //     />
          //   );
          // })
          <ProductList<React.ElementType>
            data={products}
            renderItem={({ product }: { product: ProductModel }) => (
              <ProductInfoCard product={product} navigation={navigation} />
            )}
            keyExtractor={(item: ProductModel) => item.id}
          />
        )}
        <AuthButton onPress={() => fetchProducts(0, 10)}>Fetch</AuthButton>
      </HomeScreenContainer>
    </>
  );
};

export default HomeScreen;
