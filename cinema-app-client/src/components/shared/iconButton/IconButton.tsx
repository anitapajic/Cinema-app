import { IconProp } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IconButtonStyled } from "./IconButton.styled";
import { IconButtonType } from "../../../utils/enums";

export type IconButtonProps = {
    icon: IconProp;
    onClickHandler?: () => void;
    variant?: IconButtonType;
  }

export default function IconButton({icon, onClickHandler, variant} : IconButtonProps){
    return (
        <IconButtonStyled onClick={onClickHandler} variant={variant}>
            <FontAwesomeIcon icon={icon} />
        </IconButtonStyled>
    )
}