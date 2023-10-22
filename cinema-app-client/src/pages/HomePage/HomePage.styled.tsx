import { styled } from "styled-components";

export const SelectInput = styled.select`
  margin-left: 3px;
  margin-bottom: 5px;
  padding: 8px;
  font-size: 16px;
  margin-right: 4px;
  border: 1.5px solid ${({ theme }) => theme.colors.main};
`;

export const SelectWrapper = styled.div`
  display: flex;
  justify-content: space-between;
`;
