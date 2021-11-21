// import React from "react";
// import { ThemeProvider } from "styled-components/native";
// import { AppContainer } from "./src/components/AppContainer.component";
// import NavigationHost, { Navigation } from "./src/infrastructures/navigation_copy";
// import { theme } from "./src/infrastructures/themes";
// import AuthenticationContextProvider from "./src/services/authentication/repo/Authentication.context";
// import LoginScreen from "./src/services/authentication/screens/LoginScreen.authentication";
// import RegisterScreen from "./src/services/authentication/screens/RegisterScreen.authentication";
// import {
//   Lato_400Regular,
//   Lato_400Regular_Italic,
//   Lato_700Bold,
//   Lato_700Bold_Italic,
// } from "@expo-google-fonts/lato";
// import { useFonts } from "expo-font";
// import AppLoading from "expo-app-loading";

// export default function App() {
//   let [fontsLoaded, error] = useFonts({
//     Lato_400Regular,
//     Lato_400Regular_Italic,
//     Lato_700Bold,
//     Lato_700Bold_Italic,
//   });

//   if (!fontsLoaded) {
//     return <AppLoading />;
//   }

//   return (
//     <ThemeProvider theme={theme}>
//       <AppContainer>
//         <AuthenticationContextProvider>
//           <LoginScreen />
//         </AuthenticationContextProvider>
//       </AppContainer>
//     </ThemeProvider>
//   );
// }

import React from "react";
import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import { RootStackParamList } from "./src/infrastructures/navigation/params";
import AuthScreen from "./src/infrastructures/navigation/screens/AuthScreen";
import MainScreen from "./src/infrastructures/navigation/screens/MainScreen";

const Stack = createStackNavigator<RootStackParamList>();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Auth" component={AuthScreen} />
        <Stack.Screen name="Main" component={MainScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
