import React from "react";
import LoginScreen from "../../services/authentication/screens/LoginScreen.authentication";
import RegisterScreen from "../../services/authentication/screens/RegisterScreen.authentication";
import { createStackNavigator } from "@react-navigation/stack";

const AuthStack = createStackNavigator();

const AuthNavigator = () => (
  <AuthStack.Navigator
    screenOptions={{
      headerShown: false,
    }}
  >
    <AuthStack.Screen name="Register" component={RegisterScreen} />
    <AuthStack.Screen name="Login" component={LoginScreen} />
  </AuthStack.Navigator>
);

export default AuthNavigator;
