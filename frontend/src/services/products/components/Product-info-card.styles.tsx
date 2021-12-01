import styled from "styled-components/native";
import { Button, TextInput } from "react-native-paper";
import { Text } from "../../../components/typography/text.component";
import { themeColor } from "../../../infrastructure/themes/ThemeColor.themes";
import { Card } from "react-native-paper";

export const HomeScreenContainer = styled.ScrollView`
  background-color: rgba(255, 255, 255, 0.7);
  padding: ${(props) => props.theme.space[4]};
  margin-top: ${(props) => props.theme.space[2]};
`;

export const ProductCard = styled(Card)`
  background-color: ${themeColor.bg.primary};
  width: 88%;
  align-self: center;
  margin: ${(props) => props.theme.space[2]};
`;

export const ProductCardCover = styled(Card.Cover)`
  padding: ${(props) => props.theme.space[3]};
  background-color: ${themeColor.bg.primary};
`;

export const ProductImage = styled.Image`
  /* width: 275px; */
  height: 150px;
  background-color: ${themeColor.bg.secondary};
`;

export const Address = styled.Text`
  font-family: ${(props) => props.theme.fonts.body};
  font-size: ${(props) => props.theme.fontSizes.caption};
`;

export const Info = styled.View`
  padding: ${(props) => props.theme.space[3]};
`;

export const Rating = styled.View`
  flex-direction: row;
  padding-top: ${(props) => props.theme.space[2]};
  padding-bottom: ${(props) => props.theme.space[2]};
`;

export const Section = styled.View`
  flex-direction: row;
  align-items: center;
`;

export const SectionEnd = styled.View`
  flex: 1;
  flex-direction: row;
  justify-content: flex-end;
`;
