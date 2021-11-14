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
} from "../components/Authentication.components";
import { AuthenticationContext } from "../repo/Authentication.context";

const RegisterScreen = () => {
  const [email, setEmail] = useState("a@g.c");
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
        {error ? <Text variant={TextType.error}>{error}</Text> : null}
        <AuthButton onPress={registerHandler}>Register</AuthButton>
        <AuthLink>Login</AuthLink>
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

export default RegisterScreen;
