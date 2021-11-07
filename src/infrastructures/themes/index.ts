// import { DefaultTheme } from "styled-components/native";
import { DefaultTheme } from "styled-components/native";
import { themeColor, ThemeColor } from "./ThemeColor.themes";

declare module "styled-components" {
  export interface DefaultTheme {
    themeColor: ThemeColor;
  }
}

export const theme: DefaultTheme = {
  themeColor: themeColor,
};
