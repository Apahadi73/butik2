import { StatusBar } from "expo-status-bar";
import React from "react";
import { StyleSheet, Text, View } from "react-native";
import { ThemeProvider } from "styled-components/native";
import { AppContainer } from "./src/components/AppContainer.component";
import { theme } from "./src/infrastructures/themes";
import LoginScreen from "./src/services/authentication/screens/LoginScreen.authentication";
import RegisterScreen from "./src/services/authentication/screens/RegisterScreen.authentication";

export default function App() {
  return (
    <ThemeProvider theme={theme}>
      <AppContainer>
        <RegisterScreen />
      </AppContainer>
    </ThemeProvider>
  );
}
