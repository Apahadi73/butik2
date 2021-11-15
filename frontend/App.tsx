import React from "react";
import { ThemeProvider } from "styled-components/native";
import { AppContainer } from "./src/components/AppContainer.component";
import { theme } from "./src/infrastructures/themes";
import AuthenticationContextProvider from "./src/services/authentication/repo/Authentication.context";
import LoginScreen from "./src/services/authentication/screens/LoginScreen.authentication";
import RegisterScreen from "./src/services/authentication/screens/RegisterScreen.authentication";

export default function App() {
  return (
    <ThemeProvider theme={theme}>
      <AppContainer>
        <AuthenticationContextProvider>
          <LoginScreen />
        </AuthenticationContextProvider>
      </AppContainer>
    </ThemeProvider>
  );
}
