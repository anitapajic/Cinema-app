import styled from "styled-components";

export const Card = styled.article`
  width: 200px;
  padding: 20px;
  position: relative;
  border: 2px solid ${({ theme }) => theme.colors.main};
  margin: 20px;
  text-align: center;
  h3 {
    margin-right: 3px;
    color: ${({ theme }) => theme.colors.secondColor};
    font-size: 20px;
  }

  &:hover {
    border: 1.5px solid ${({ theme }) => theme.colors.secondColor};
    cursor: pointer;
  }
  border-radius: 10px;
`;
