import React, { useContext } from "react";
import { NavigationContainer } from "@react-navigation/native";

// import { AppNavigator } from "./app.navigator.tsx";
import { AccountNavigator } from "./authentication.navigator";
import { AuthenticationContext } from "../../services/authentication/repo/Authentication.context";

export const Navigation = () => {
  // we need isAuthenticated to determine which navigation stack to show
  const { isLoggedIn } = useContext(AuthenticationContext);
  console.log(isLoggedIn);

  return (
    <NavigationContainer>
      <AccountNavigator />
    </NavigationContainer>
  );
};
