import styled from "styled-components/native";

export const SpaceBetweenRow = styled.View`
  flex: 1;
  flex-direction: row;
  padding-top: ${(props) => props.theme.space[2]};
  padding-bottom: ${(props) => props.theme.space[2]};
  justify-content: space-between;
`;

export const RowContainerSpacer = styled.View`
  width: 100%;
`;
