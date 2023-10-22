import { ButtonSize, ButtonType } from "../../../utils/enums";
import { ButtonStyle } from "./Button.styled";

interface ButtonProps {
    size?: ButtonSize;
    label?: string;
    variant: ButtonType;
    onClickHandler?: (event: React.MouseEvent<HTMLButtonElement>) => void;
    type?: 'button' | 'submit';
  }

  export default function Button({size, label, variant, onClickHandler, type} : ButtonProps){
    return (
        <ButtonStyle size={size} variant={variant} onClick={onClickHandler} type={type}>
        {label}
        </ButtonStyle>
    )
  }
