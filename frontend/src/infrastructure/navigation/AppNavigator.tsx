import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { AppStackParamList } from "./types";
import React from "react";
import AuthNavigator from "./AuthNavigator";

const AppStack = createNativeStackNavigator<AppStackParamList>();

export default function AppNavigator() {
  return <AuthNavigator />;
}
