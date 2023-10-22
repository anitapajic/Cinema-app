import { useState } from "react";
import { ButtonType, ButtonSize } from "../../../utils/enums";
import Button from "../button/Button";
import { ButtonGroup, StyledForm, StyledInput, StyledLabel } from "../styled/SharedStyles.styled";
import { GenreSelect } from "../../movies/MovieForm/MovieForm.styled";

export type FormInput = {
    label?: string;
    name?: string;
    onChange?: (name: string, value: string | number | null) => void;
    customRender: (input: FormInput, handleChange: (e: any) => void) => JSX.Element;
    isValid?: (value: string) => boolean
}

export type GenericFormProps = {
    inputs : FormInput[];
    onSubmit: () => void;
    onCancel: () => void;
    submitButtonLabel: string;
    cancelButtonLabel: string;
}

export default function GenericForm({inputs, onSubmit, onCancel, submitButtonLabel, cancelButtonLabel} : GenericFormProps) {

    const handleSubmit = () => {
        onSubmit();       
    };

      return (
        <StyledForm onSubmit={handleSubmit}>
          {inputs.map((input, index) => (
            <StyledLabel key={index}>
              {input.label}
              {input.customRender(input, (value) => input.onChange?.(input.name ?? "", value))}
            </StyledLabel>
          ))}
          <ButtonGroup>
            <Button
              variant={ButtonType.PRIMARY}
              label={submitButtonLabel}
              size={ButtonSize.STANDARD}
              onClickHandler={onSubmit}
            />
            <Button
              variant={ButtonType.SECONDARY}
              label={cancelButtonLabel}
              size={ButtonSize.STANDARD}
              onClickHandler={onCancel}
            />
          </ButtonGroup>
        </StyledForm>
      );
}