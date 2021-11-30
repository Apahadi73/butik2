import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { AppStackParamList } from "./types";
import React, { useContext } from "react";
import AuthNavigator from "./AuthNavigator";
import { AuthenticationContext } from "../../services/authentication/repo/Authentication.context";
import BottomTabNavigator from "./BottomTabNavigator";

const AppStack = createNativeStackNavigator<AppStackParamList>();

export default function AppNavigator() {
  // const { isLoggedIn } = useContext(AuthenticationContext);
  let isLoggedIn = true;

  return isLoggedIn ? <BottomTabNavigator /> : <AuthNavigator />;
}
