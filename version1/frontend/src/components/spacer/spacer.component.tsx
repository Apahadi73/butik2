import React from "react";
import { View } from "react-native";
import styled, { useTheme, DefaultTheme } from "styled-components/native";

// different sizes
export enum SizeEnum {
  small = 1,
  medium = 2,
  large = 3,
  xl = 4,
  xxl = 5,
}

// different margin position
export enum MarginType {
  top = "marginTop",
  left = "marginLeft",
  right = "marginRight",
  bottom = "marginBottom",
}

// computes dimension
const getVariant = (
  position: MarginType,
  size: SizeEnum,
  theme: DefaultTheme
) => {
  const property = position;
  const value = theme.space[size];
  return `${property}:${value}`;
};

type SpacerViewProps = {
  variant: string;
};
const SpacerView = styled.View<SpacerViewProps>`
  ${({ variant }) => variant};
`;

// computes required dimension variation first and then fill the SpacerView
export const Spacer = (
  position: MarginType = MarginType.top,
  size: SizeEnum = SizeEnum.small,
  children: React.ReactNode
) => {
  const theme = useTheme();
  const variant: string = getVariant(position, size, theme);
  return <SpacerView variant={variant}>{children}</SpacerView>;
};

// default props for the Spacer
Spacer.defaultProps = {
  position: MarginType.top,
  size: SizeEnum,
  children: View,
};
