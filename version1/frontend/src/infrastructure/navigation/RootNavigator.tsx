import { createNativeStackNavigator } from "@react-navigation/native-stack";
import ModalScreen from "../../screens/ModalScreen";
import NotFoundScreen from "../../screens/NotFoundScreen";
import BottomTabNavigator from "./BottomTabNavigator";
import { RootStackParamList } from "./types";
import React from "react";
import AppNavigator from "./AppNavigator";
import AuthNavigator from "./AuthNavigator";

/**
 * A root stack navigator is often used for displaying modals on top of all other content.
 * https://reactnavigation.org/docs/modal
 */
const Stack = createNativeStackNavigator<RootStackParamList>();

export default function RootNavigator() {
  return (
    <Stack.Navigator>
      <Stack.Screen
        name="Root"
        component={AppNavigator}
        options={{ headerShown: false }}
      />
      <Stack.Screen
        name="NotFound"
        component={NotFoundScreen}
        options={{ title: "Oops!" }}
      />
      <Stack.Group screenOptions={{ presentation: "modal" }}>
        <Stack.Screen name="Modal" component={ModalScreen} />
      </Stack.Group>
    </Stack.Navigator>
  );
}
