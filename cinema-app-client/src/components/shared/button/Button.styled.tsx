import styled from 'styled-components';
import { ButtonSize, ButtonType} from '../../../utils/enums';

export const ButtonStyle = styled.button<{ size?: ButtonSize, variant: ButtonType}>`
  background-color: ${({ theme, variant }) => (variant === ButtonType.PRIMARY  ? theme.colors.main : theme.colors.secondColor)};
  color: ${({ theme, variant }) => (variant === ButtonType.PRIMARY ? theme.colors.secondColor : theme.colors.textColor)};
  padding: ${({ size }) => (size === ButtonSize.LARGE ? '15px 30px' : size === ButtonSize.SMALL ? '5px 10px' : '10px 20px')};
  border: none;
  border-radius: ${({theme}) => theme.radius.buttons};
  cursor: pointer;
  transition: background-color 0.3s ease-in-out, color 0.3s ease-in-out;
  font-size: ${({ theme, size }) => (size === ButtonSize.LARGE ? theme.fontSizes.large : size === ButtonSize.SMALL ? theme.fontSizes.small : theme.fontSizes.standard)};
  margin: 5px;
  font-weight : bold;
`;

