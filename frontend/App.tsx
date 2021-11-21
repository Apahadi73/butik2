import React from "react";
import { ThemeProvider } from "styled-components/native";
import { AppContainer } from "./src/components/AppContainer.component";
import { Navigation } from "./src/infrastructures/navigation";
import { theme } from "./src/infrastructures/themes";
import AuthenticationContextProvider from "./src/services/authentication/repo/Authentication.context";
import LoginScreen from "./src/services/authentication/screens/LoginScreen.authentication";
import RegisterScreen from "./src/services/authentication/screens/RegisterScreen.authentication";
import {
  Lato_400Regular,
  Lato_400Regular_Italic,
  Lato_700Bold,
  Lato_700Bold_Italic,
} from "@expo-google-fonts/lato";
import { useFonts } from "expo-font";
import AppLoading from "expo-app-loading";

export default function App() {
  let [fontsLoaded, error] = useFonts({
    Lato_400Regular,
    Lato_400Regular_Italic,
    Lato_700Bold,
    Lato_700Bold_Italic,
  });

  if (!fontsLoaded) {
    return <AppLoading />;
  }

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
