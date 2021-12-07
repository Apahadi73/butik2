import styled from "styled-components/native";

export const SpaceBetweenRow = styled.View`
  flex: 1;
  flex-direction: row;
  padding: ${(props) => props.theme.space[2]};
  padding: ${(props) => props.theme.space[2]};
  margin: ${(props) => props.theme.space[1]};
  justify-content: space-between;
  align-items: center;
`;

export const RowContainerSpacer = styled.View`
  width: 100%;
`;
