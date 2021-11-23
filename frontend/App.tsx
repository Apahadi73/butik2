import { useFonts } from "expo-font";
import {
  Lato_400Regular,
  Lato_400Regular_Italic,
  Lato_700Bold,
  Lato_700Bold_Italic,
} from "@expo-google-fonts/lato";
import { StatusBar } from "expo-status-bar";
import React from "react";
import { SafeAreaProvider } from "react-native-safe-area-context";
import { ThemeProvider } from "styled-components/native";

import Navigation from "./src/infrastructure/navigation";
import { theme } from "./src/infrastructure/themes";
import AuthenticationContextProvider from "./src/services/authentication/repo/Authentication.context";
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
    <SafeAreaProvider>
      <ThemeProvider theme={theme}>
        <AuthenticationContextProvider>
          <Navigation />
        </AuthenticationContextProvider>
      </ThemeProvider>
      <StatusBar />
    </SafeAreaProvider>
  );
}
