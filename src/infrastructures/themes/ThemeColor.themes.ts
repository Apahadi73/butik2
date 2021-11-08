interface UI {
  primary: string;
  secondary: string;
  tertiary: string;
  quaternary: string;
  disabled: string;
  error: string;
  success: string;
  white: string;
}

interface Text {
  primary: string;
  secondary: string;
  disabled: string;
  inverse: string;
  error: string;
  success: string;
}

interface Background {
  primary: string;
  secondary: string;
}
export interface ThemeColor {
  ui: UI;
  bg: Background;
  text: Text;
}

export const themeColor: ThemeColor = {
  ui: {
    primary: "#082032",
    secondary: "#757575",
    tertiary: "#F1F1F1",
    quaternary: "#FFFFFF",
    disabled: "#DEDEDE",
    error: "#D0421B",
    success: "#138000",
    white: "#FFFFFF",
  },
  bg: {
    primary: "#FFFFFF",
    secondary: "#F1F1F1",
  },
  text: {
    primary: "#262626",
    secondary: "#5C7AEA",
    disabled: "#9C9C9C",
    inverse: "#FFFFFF",
    error: "#D0421B",
    success: "#138000",
  },
};
