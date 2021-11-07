// import { DefaultTheme } from "styled-components/native";
import { DefaultTheme } from "styled-components/native";
import {
  fonts,
  Fonts,
  fontSizes,
  FontSizes,
  fontWeights,
  FontWeights,
} from "./Fonts.themes";
import { sizes } from "./Size.themes";
import { LineHeight, lineHeights, space } from "./Spacing.themes";
import { themeColor, ThemeColor } from "./ThemeColor.themes";

declare module "styled-components" {
  export interface DefaultTheme {
    themeColor: ThemeColor;
    space: string[];
    lineHeights: LineHeight;
    sizes: string[];
    fonts: Fonts;
    fontSizes: FontSizes;
    fontWeights: FontWeights;
  }
}

export const theme: DefaultTheme = {
  themeColor: themeColor,
  space: space,
  lineHeights: lineHeights,
  sizes: sizes,
  fonts: fonts,
  fontSizes: fontSizes,
  fontWeights: fontWeights,
};
