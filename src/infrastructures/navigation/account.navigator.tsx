import React from "react";
import { createStackNavigator } from "@react-navigation/stack";

import { LoginScreen } from "../../services/authentication/screens/LoginScreen.authentication";

const Stack = createStackNavigator();

export const AccountNavigator = () => (
  <Stack.Navigator headerMode="none">
    <Stack.Screen name="Login" component={LoginScreen} />
    {/* <Stack.Screen name="Register" component={RegisterScreen} /> */}
  </Stack.Navigator>
);
