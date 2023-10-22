import { ButtonSize, ButtonType } from "../../../utils/enums";
import Button from "../button/Button";
import { ButtonGroup } from "../styled/SharedStyles.styled";
import {
  ConfirmationLabelWrapper,
  MarginedStyledLabel,
} from "./ConfirmationForm.styled";

export type ConfirmationFormProps = {
  label: string;
  confirmLabel: string;
  onConfirm: () => void;
  onCancel: () => void;
};

export default function ConfirmationForm({
  label,
  confirmLabel,
  onConfirm,
  onCancel,
}: ConfirmationFormProps) {
  return (
    <>
      <ConfirmationLabelWrapper>
        <MarginedStyledLabel>{label}</MarginedStyledLabel>
      </ConfirmationLabelWrapper>
      <ButtonGroup>
        <Button
          variant={ButtonType.PRIMARY}
          label={confirmLabel}
          size={ButtonSize.STANDARD}
          onClickHandler={onConfirm}
        />
        <Button
          variant={ButtonType.SECONDARY}
          label="Cancel"
          size={ButtonSize.STANDARD}
          onClickHandler={onCancel}
        />
      </ButtonGroup>
    </>
  );
}
