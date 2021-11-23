import { StackNavigationProp } from "@react-navigation/stack";
import React, { useState, useEffect, useContext } from "react";
import { Image, StyleSheet } from "react-native";
import images from "../../../assets/images";
import { AppLogoContainer } from "../../../components/image/Image.component";
import { Text, TextType } from "../../../components/typography/Text.component";
import { AuthStackParamList } from "../../../infrastructure/navigation/types";
import { AppContainer } from "../../../components/AppContainer.component";
import {
  AccountContainer,
  AuthButton,
  AuthInput,
  AuthLink,
  ErrorContainer,
} from "../components/Authentication.components";
import { AuthenticationContext } from "../repo/Authentication.context";

export interface RegisterProps {
  navigation: StackNavigationProp<AuthStackParamList, "Register">;
}

const RegistrationScreen: React.FC<RegisterProps> = ({ navigation }) => {
  const [email, setEmail] = useState("random@patriots.uttyler.edu");
  const [password, setPassword] = useState("password");
  const { currentUser, isLoading, error, register } = useContext(
    AuthenticationContext
  );
  useEffect(() => {}, []);

  const registerHandler = async (e: any) => {
    e.preventDefault();
    await register(email, password);
  };
  return (
    <>
      <AppContainer>
        <AccountContainer>
          <AppLogoContainer>
            <Image source={images.app_logo} style={styles.logo} />
          </AppLogoContainer>
          <AuthInput
            label="E-mail"
            value={email}
            textContentType="emailAddress"
            keyboardType="email-address"
            autoCapitalize="none"
            onChangeText={(u: string) => setEmail(u)}
          />
          <AuthInput
            label="Password"
            value={password}
            textContentType="password"
            secureTextEntry
            autoCapitalize="none"
            onChangeText={(p) => setPassword(p)}
          />
          {error ? (
            <ErrorContainer size="large">
              <Text variant={TextType.error}>{error}</Text>
            </ErrorContainer>
          ) : null}
          <AuthButton onPress={registerHandler}>Register</AuthButton>
          <AuthLink onPress={() => navigation.navigate("Login")}>
            Login
          </AuthLink>
        </AccountContainer>
      </AppContainer>
    </>
  );
};

const styles = StyleSheet.create({
  logo: {
    width: 100,
    height: 100,
  },
});

export default RegistrationScreen;
