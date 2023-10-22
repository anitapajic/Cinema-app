import { useState } from "react";
import MovieScreening, {
  NewMovieScreening,
} from "../../../models/MovieScreenings";
import { createInitialScreeningState } from "./ScreeningForm.data";
import GenericForm, { FormInput } from "../../shared/genericForm/GenericForm";
import { renderStyledInput } from "../../shared/genericForm/GenericForm.helper";
import { showToast } from "../../shared/toast/CustomToast";

export type ScreeningFormProps = {
  onSubmit: (screening: NewMovieScreening) => void;
  onCancel: () => void;
  initialScreening: MovieScreening | null;
};

export type ScreeningState = {
  movieIdValue: number | null;
  dateTimeValue: string;
  priceValue: string | number;
  seatColValue: string | number;
  seatRowValue: string | number;
};
export interface ScreeningValidationState {
  movieIdValue: boolean;
  dateTimeValue: boolean;
  priceValue: boolean;
  seatColValue: boolean;
  seatRowValue: boolean;
}

export default function ScreeningForm({
  onSubmit,
  onCancel,
  initialScreening,
}: ScreeningFormProps) {
  const [screening, setScreening] = useState<ScreeningState>(
    createInitialScreeningState(initialScreening)
  );
  const [validationState, setValidationState] =
    useState<ScreeningValidationState>({
      movieIdValue: initialScreening ? true : false,
      dateTimeValue: initialScreening ? true : false,
      priceValue: initialScreening ? true : false,
      seatColValue: initialScreening ? true : false,
      seatRowValue: initialScreening ? true : false,
    });

  const isEditMode = Boolean(initialScreening);
  const buttonLabel = isEditMode ? "Edit Screening" : "Add New Screening";

  const validateNumberValue = (value: string) => {
    const numberRegex = /^\d*\.?\d+$/;
    return numberRegex.test(value);
  };

  const validateDateValue = (value: string) => {
    const inputDateTime = new Date(value);
    const currentDateTime = new Date();
    return inputDateTime >= currentDateTime;
  };

  const handleInputChange = (name: string, value: string | number | null) => {
    const inputConfig = formInputs.find((input) => input.name === name);
    let isValid = true;
    if (inputConfig?.isValid) {
      isValid = inputConfig.isValid(String(value));
    }

    setValidationState((prev) => ({
      ...prev,
      [name]: isValid,
    }));

    setScreening((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const formInputs: FormInput[] = [
    {
      label: "Movie ID",
      name: "movieIdValue",
      customRender: () => (
        <>
          {renderStyledInput({
            type: "text",
            name: "movieIdValue",
            value:
              screening.movieIdValue !== null ? screening.movieIdValue : "",
            onChange: handleInputChange,
            required: true,
            isValid: validationState.movieIdValue,
          })}
        </>
      ),
      isValid: validateNumberValue,
    },
    {
      label: "Date and Time",
      name: "dateTimeValue",
      customRender: () => (
        <>
          {renderStyledInput({
            type: "datetime-local",
            name: "dateTimeValue",
            value: screening.dateTimeValue,
            onChange: handleInputChange,
            required: true,
            isValid: validationState.dateTimeValue,
          })}
        </>
      ),
      isValid: validateDateValue,
    },
    {
      label: "Price",
      name: "priceValue",
      customRender: () => (
        <>
          {renderStyledInput({
            type: "text",
            name: "priceValue",
            value: screening.priceValue,
            onChange: handleInputChange,
            required: true,
            isValid: validationState.priceValue,
          })}
        </>
      ),
      isValid: validateNumberValue,
    },
    {
      label: "Number of column seats",
      name: "seatColValue",
      customRender: () => (
        <>
          {renderStyledInput({
            type: "text",
            name: "seatColValue",
            value: screening.seatColValue,
            onChange: handleInputChange,
            required: true,
            isValid: validationState.seatColValue,
          })}
        </>
      ),
      isValid: validateNumberValue,
    },
    {
      label: "Number of row seats",
      name: "seatRowValue",
      customRender: () => (
        <>
          {renderStyledInput({
            type: "text",
            name: "seatRowValue",
            value: screening.seatRowValue,
            onChange: handleInputChange,
            required: true,
            isValid: validationState.seatRowValue,
          })}
        </>
      ),
      isValid: validateNumberValue,
    },
  ];

  const handleSubmit = () => {
    const isFormValid = Object.values(validationState).every(
      (isValid) => isValid
    );

    if (!isFormValid) {
      showToast("Please correct invalid inputs before submitting!");
      return;
    }

    const newScreening: NewMovieScreening = {
      id: initialScreening ? initialScreening.id : null,
      dateTime: screening.dateTimeValue,
      price: +screening.priceValue,
      seatCol: +screening.seatColValue,
      seatRow: +screening.seatRowValue,
      movieId: screening.movieIdValue,
    };

    onSubmit(newScreening);
  };

  return (
    <GenericForm
      inputs={formInputs}
      onSubmit={handleSubmit}
      onCancel={onCancel}
      submitButtonLabel={buttonLabel}
      cancelButtonLabel="Cancel"
    />
  );
}
