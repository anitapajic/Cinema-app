import styled from "styled-components";
import { IconButtonType } from "../../../utils/enums";

export const IconButtonStyled = styled.button<{variant? : IconButtonType}>`
  background: none;
  border: none;
  cursor: pointer;
  color: ${({ theme, variant }) => (variant === IconButtonType.DARK ? theme.colors.secondColor : theme.colors.textColor)};

  &:hover{
    color: ${({ theme}) => theme.colors.main};
  }
`;