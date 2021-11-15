import React, { useState, useEffect, useContext } from "react";
import { Image, StyleSheet } from "react-native";
import images from "../../../assets/images";
import { AppLogoContainer } from "../../../components/image/Image.component";
import { Text, TextType } from "../../../components/typography/Text.component";
import {
  AccountContainer,
  AuthButton,
  AuthInput,
  AuthLink,
  ErrorContainer,
} from "../components/Authentication.components";
import { AuthenticationContext } from "../repo/Authentication.context";

const LoginScreen = () => {
  const [email, setEmail] = useState("random@patriots.uttyler.edu");
  const [password, setPassword] = useState("password");
  const { currentUser, isLoading, error, login } = useContext(
    AuthenticationContext
  );
  useEffect(() => {}, []);

  const loginHandler = async (e: any) => {
    e.preventDefault();
    await login(email, password);
  };

  return (
    <>
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
        <AuthButton onPress={loginHandler}>Login</AuthButton>
        <AuthLink>Register</AuthLink>
      </AccountContainer>
    </>
  );
};

const styles = StyleSheet.create({
  logo: {
    width: 100,
    height: 100,
  },
});

export default LoginScreen;
