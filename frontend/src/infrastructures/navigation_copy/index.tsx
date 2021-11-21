import React, { useContext } from "react";
import { NavigationContainer } from "@react-navigation/native";

// import { AppNavigator } from "./app.navigator.tsx";
import { AuthenticationContext } from "../../services/authentication/repo/Authentication.context";
import AuthNavigator from "./authentication.navigator";

const NavigationHost = () => {
  // we need isAuthenticated to determine which navigation stack to show
  const { isLoggedIn } = useContext(AuthenticationContext);
  console.log(isLoggedIn);

  return (
    <NavigationContainer>
      <AuthNavigator />
    </NavigationContainer>
  );
};
export default NavigationHost;
