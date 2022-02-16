import { FontAwesome } from "@expo/vector-icons";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { Pressable } from "react-native";
import TabOneScreen from "../../screens/TabOneScreen";
import TabTwoScreen from "../../screens/TabTwoScreen";
import TabBarIcon from "./TabBarIcon";
import { RootTabParamList, RootTabScreenProps } from "./types";
import React from "react";
import { themeColor } from "../themes/ThemeColor.themes";
import HomeScreen from "../../services/products/screens/HomeScreen.home";
import HomeNavigator from "./HomeNavigator";
import CartScreen from "../../services/cart/screens/CartScreen.cart";

/**
 * A bottom tab navigator displays tab buttons on the bottom of the display to switch screens.
 * https://reactnavigation.org/docs/bottom-tab-navigator
 */
const BottomTab = createBottomTabNavigator<RootTabParamList>();

export default function BottomTabNavigator() {
  return (
    <BottomTab.Navigator
      initialRouteName="Home"
      screenOptions={{
        tabBarActiveTintColor: themeColor.ui.primary,
      }}
    >
      <BottomTab.Screen
        name="Home"
        component={HomeNavigator}
        options={({ navigation }: RootTabScreenProps<"Home">) => ({
          tabBarIcon: ({ color }) => <TabBarIcon name="home" color={color} />,
          tabBarLabel: "Home",
          headerShown: false,
        })}
      />
      <BottomTab.Screen
        name="Cart"
        component={CartScreen}
        options={{
          title: "Cart",
          tabBarIcon: ({ color }) => (
            <TabBarIcon name="shopping-cart" color={color} />
          ),
          headerShown: false,
        }}
      />
      <BottomTab.Screen
        name="TabTwo"
        component={TabTwoScreen}
        options={{
          title: "Tab Two",
          tabBarIcon: ({ color }) => <TabBarIcon name="code" color={color} />,
        }}
      />
    </BottomTab.Navigator>
  );
}
