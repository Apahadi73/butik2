import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { HomeStackParamList } from "./types";
import React from "react";
import HomeScreen from "../../services/products/screens/HomeScreen.home";
import ProductDetailsScreen from "../../services/products/screens/ProductDetailScreen.home";

/**
 * A root stack navigator is often used for displaying modals on top of all other content.
 * https://reactnavigation.org/docs/modal
 */
const HomeStack = createNativeStackNavigator<HomeStackParamList>();

export default function HomeNavigator() {
  return (
    <HomeStack.Navigator>
      <HomeStack.Screen
        name="HomeScreen"
        component={HomeScreen}
        options={{ headerShown: false }}
      />
      <HomeStack.Screen
        name="ProductDetailScreen"
        component={ProductDetailsScreen}
        options={{ headerShown: false }}
      />
    </HomeStack.Navigator>
  );
}
