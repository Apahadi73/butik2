import { Button, TextInput } from "react-native-paper";
import styled from "styled-components/native";
import { themeColor } from "../../../infrastructure/themes/ThemeColor.themes";

export const AddButton = styled(Button).attrs({
  color: themeColor.ui.white,
})`
  padding: ${(props) => props.theme.space[2]};
  margin: ${(props) => props.theme.space[2]};
  background-color: ${themeColor.ui.primary};
`;
