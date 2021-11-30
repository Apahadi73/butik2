import React, { useEffect, useState } from "react";
import { StyleSheet, Image } from "react-native";
import images from "../../../assets/images";
import { AppContainer } from "../../../components/AppContainer.component";
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
import { StackNavigationProp } from "@react-navigation/stack";
import { AuthStackParamList } from "../../../infrastructure/navigation/types";

export interface LoginProps {
  navigation: StackNavigationProp<AuthStackParamList, "Login">;
}
const LoginScreen: React.FC<LoginProps> = ({ navigation }) => {
  const [email, setEmail] = useState("random@patriots.uttyler.edu");
  const [password, setPassword] = useState("password");
  const { currentUser, isLoading, error, login } = React.useContext(
    AuthenticationContext
  );
  useEffect(() => {}, [console.log(navigation)]);

  const loginHandler = async (e: any) => {
    e.preventDefault();
    await login(email, password);
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
          <AuthButton onPress={loginHandler}>Login</AuthButton>
          <AuthLink onPress={() => navigation.navigate("Register")}>
            Register
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

export default LoginScreen;
