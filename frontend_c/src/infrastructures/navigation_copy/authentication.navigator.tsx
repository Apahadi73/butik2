import React from "react";
import LoginScreen from "../../services/authentication/screens/LoginScreen.authentication";
import RegisterScreen from "../../services/authentication/screens/RegisterScreen.authentication";
import { createStackNavigator } from "@react-navigation/stack";

const Stack = createStackNavigator();

const AccountNavigator = () => (
  <Stack.Navigator headerMode="none">
    <Stack.Screen name="Register" component={RegisterScreen} />
    <Stack.Screen name="Login" component={LoginScreen} />
  </Stack.Navigator>
);

export default AccountNavigator;
