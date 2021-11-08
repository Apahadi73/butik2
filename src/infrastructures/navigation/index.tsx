import React, { useContext } from "react";
import { NavigationContainer } from "@react-navigation/native";

// import { AppNavigator } from "./app.navigator.tsx";
import { AccountNavigator } from "./authentication.navigator";

// import { AuthenticationContext } from "../../services/authentication/authentication.context";

export const Navigation = () => {
  // we need isAuthenticated to determine which navigation stack to show
  // const { isAuthenticated } = useContext(AuthenticationContext);
  const isAuthenticated = false;

  return (
    <NavigationContainer>
      <AccountNavigator />
    </NavigationContainer>
  );
};
