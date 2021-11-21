import styled, { DefaultTheme } from "styled-components/native";

export enum TextType {
  body = "body",
  label = "label",
  caption = "caption",
  error = "error",
  hint = "hint",
  title = "title",
  link = "link",
}

const defaultTextStyles = (theme: DefaultTheme) => `
  font-family: ${theme.fonts.body};
  font-weight: ${theme.fontWeights.regular};
  color: ${theme.themeColor.text.primary};
  flex-wrap: wrap;
  margin-top: 0px;
  margin-bottom: 0px;
`;

const body = (theme: DefaultTheme) => `
    font-size: ${theme.fontSizes.body};
`;

const hint = (theme: DefaultTheme) => `
    font-size: ${theme.fontSizes.body};
`;

const error = (theme: DefaultTheme) => `
    color: ${theme.themeColor.text.error};
`;

const caption = (theme: DefaultTheme) => `
    font-size: ${theme.fontSizes.caption};
    font-weight: ${theme.fontWeights.bold};
`;

const title = (theme: DefaultTheme) => `
    font-size: ${theme.fontSizes.title};
    font-weight: ${theme.fontWeights.bold};
    margin: auto
`;

const label = (theme: DefaultTheme) => `
    font-family: ${theme.fonts.heading};
    font-size: ${theme.fontSizes.body};
    font-weight: ${theme.fontWeights.medium};
`;

const link = (theme: DefaultTheme) => `
    font-family: ${theme.fonts.heading};
    font-size: ${theme.fontSizes.body};
    font-weight: ${theme.fontWeights.medium};
    font-color:${theme.themeColor.text.secondary};
`;

interface Variants {
  [key: string]: (theme: DefaultTheme) => string;
}
const variants: Variants = {
  body,
  label,
  caption,
  error,
  hint,
  title,
  link,
};

type TextProps = {
  variant: TextType;
};
export const Text = styled.Text<TextProps>`
  ${({ theme }) => defaultTextStyles(theme)}
  ${({ variant, theme }) => variants[variant](theme)}
`;

Text.defaultProps = {
  variant: TextType.body,
};
