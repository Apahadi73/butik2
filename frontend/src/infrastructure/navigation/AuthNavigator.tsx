import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { AuthStackParamList } from "./types";
import React from "react";
import LoginScreen from "../../services/authentication/screens/LoginScreen.authentication";
import RegistrationScreen from "../../services/authentication/screens/RegisterScreen.authentication";

/**
 * A root stack navigator is often used for displaying modals on top of all other content.
 * https://reactnavigation.org/docs/modal
 */
const AuthStack = createNativeStackNavigator<AuthStackParamList>();

export default function AuthNavigator() {
  return (
    <AuthStack.Navigator>
      <AuthStack.Screen
        name="Login"
        component={LoginScreen}
        options={{ headerShown: false }}
      />
      <AuthStack.Screen
        name="Register"
        component={RegistrationScreen}
        options={{ headerShown: false }}
      />
    </AuthStack.Navigator>
  );
}
