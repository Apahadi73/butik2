import styled from "styled-components/native";
import { Button, TextInput } from "react-native-paper";
import { Text } from "../../../components/typography/text.component";
import { themeColor } from "../../../infrastructure/themes/ThemeColor.themes";
import { Card } from "react-native-paper";
import { FlatList } from "react-native";

export const CartScreenContainer = styled.View`
  background-color: rgba(255, 255, 255, 0.7);
  padding: ${(props) => props.theme.space[4]};
  margin-top: ${(props) => props.theme.space[2]};
`;

export const CartCard = styled(Card)`
  background-color: ${themeColor.bg.primary};
  width: 100%;
  align-self: center;
  margin: ${(props) => props.theme.space[2]};
`;

export const ProductThumbnail = styled.Image`
  width: 40px;
  height: 40px;
  background-color: ${themeColor.bg.secondary};
`;

export const CheckoutButton = styled(Button).attrs({
  color: themeColor.ui.white,
})`
  padding: ${(props) => props.theme.space[2]};
  margin: ${(props) => props.theme.space[2]};
  background-color: ${themeColor.ui.primary};
`;

export const CartList = styled(FlatList).attrs({
  contentContainerStyle: {},
})``;
