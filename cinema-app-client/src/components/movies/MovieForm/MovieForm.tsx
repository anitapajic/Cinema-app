import { useEffect, useState } from "react";
import Movie from "../../../models/Movie";
import Genre from "../../../models/Genre";
import { showToast } from "../../shared/toast/CustomToast";
import { convertToBase64 } from "../../../utils/functions/convertToBase64";
import { createInitialMovieState } from "./MovieForm.data";
import GenericForm, { FormInput } from "../../shared/genericForm/GenericForm";
import {
  renderFileInputSection,
  renderSelect,
  renderSelectedItems,
  renderStyledInput,
} from "../../shared/genericForm/GenericForm.helper";
import GenreService from "../../../services/GenreService/GenreService";

export type MovieFormProps = {
  onSubmit: (type: Movie) => void;
  onCancel: () => void;
  initialMovie: Movie | null;
};

const validateMovieName = (value: string) =>
  value.toString().length > 0 && value.toString().length < 20;

const validateMovieDuration = (value: string) => {
  const durationRegex = /^\d*\.?\d+$/;
  return durationRegex.test(value);
};

const validateMovieRating = (value: string) => {
  const ratingRegex = /^(?:[0-9]|10)(?:\.[0-9])?$/;
  return ratingRegex.test(value);
};

export interface MovieState {
  nameValue: string;
  durationValue: string;
  ratingValue: string;
  selectedGenre: string;
  genreList: Genre[];
  posterImage: string | null;
}
export interface MovieValidationState {
  nameValue: boolean;
  durationValue: boolean;
  ratingValue: boolean;
}

export default function MovieForm({
  onSubmit,
  onCancel,
  initialMovie,
}: MovieFormProps) {
  const [movie, setMovie] = useState<MovieState>(
    createInitialMovieState(initialMovie)
  );
  const [genres, setGenres] = useState<Genre[]>([]);

  const [validationState, setValidationState] = useState<MovieValidationState>({
    nameValue: initialMovie ? true : false,
    durationValue: initialMovie ? true : false,
    ratingValue: initialMovie ? true : false,
  });

  useEffect(() => {
    const fetchGenres = async () => {
      try {
        const response = await GenreService.getGenres();
        setGenres(response.data);
      } catch (error) {
        console.error("Error fetching genres: ", error);
      }
    };

    fetchGenres();
  }, []);

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

    setMovie((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const formInputs: FormInput[] = [
    {
      label: "Movie Name",
      name: "nameValue",
      customRender: () => (
        <>
          {renderStyledInput({
            type: "text",
            name: "nameValue",
            value: movie.nameValue,
            onChange: handleInputChange,
            required: true,
            isValid: validationState.nameValue,
          })}
        </>
      ),
      isValid: validateMovieName,
    },
    {
      label: "Movie Duration",
      name: "durationValue",
      customRender: () => (
        <>
          {renderStyledInput({
            type: "text",
            name: "durationValue",
            value: movie.durationValue,
            onChange: handleInputChange,
            required: true,
            isValid: validationState.durationValue,
          })}
        </>
      ),
      isValid: validateMovieDuration,
    },
    {
      label: "Movie Rating",
      name: "ratingValue",
      customRender: () => (
        <>
          {renderStyledInput({
            type: "text",
            name: "ratingValue",
            value: movie.ratingValue,
            onChange: handleInputChange,
            required: true,
            isValid: validationState.ratingValue,
          })}
        </>
      ),
      isValid: validateMovieRating,
    },
    {
      label: "Genres",
      name: "genreValue",
      customRender: () => (
        <>
          {renderSelect({
            selectedValue: movie.selectedGenre,
            handleChange: handleGenreChange,
            options: genres,
            addButtonLabel: "Add genre",
            handleAddClick: handleAddGenre,
          })}
        </>
      ),
    },
    {
      label: "Selected Genres",
      name: "selectedGenresValue",
      customRender: () => (
        <>
          {renderSelectedItems<Genre>({
            listItem: movie.genreList,
            handleItemRemove: handleRemoveGenre,
          })}
        </>
      ),
    },
    {
      label: "Poster Image: ",
      name: "posterImage",
      customRender: () =>
        renderFileInputSection({
          handleChange: handleImageChange,
          fileSource: movie.posterImage,
          altText: "Existing Movie Poster",
        }),
    },
  ];

  const handleGenreChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setMovie((prev) => ({
      ...prev,
      selectedGenre: event.target.value,
    }));
  };

  const handleAddGenre = () => {
    if (movie.selectedGenre) {
      const genreToAdd = genres.find((g) => g.type === movie.selectedGenre);
      console.log("genreTypes ", genres);
      if (
        genreToAdd &&
        !movie.genreList.some((genre) => genre.type === movie.selectedGenre)
      ) {
        setMovie((prev) => ({
          ...prev,
          genreList: [...prev.genreList, genreToAdd],
          selectedGenre: "",
        }));
      } else {
        showToast("Genre already added!");
      }
    }
  };

  const handleImageChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      try {
        const base64Image = await convertToBase64(file);
        setMovie((prev) => ({
          ...prev,
          posterImage: base64Image,
        }));
      } catch (error) {
        console.error("Error converting image:", error);
      }
    }
  };
  const handleRemoveGenre = (genreToRemove: Genre) => {
    setMovie((prev) => ({
      ...prev,
      genreList: prev.genreList.filter(
        (genre) => genre.id !== genreToRemove.id
      ),
    }));
  };

  const handleSubmit = () => {
    const isFormValid = Object.values(validationState).every(
      (isValid) => isValid
    );

    if (!isFormValid) {
      showToast("Please correct invalid inputs before submitting!");
      return;
    }

    const newMovie: Movie = {
      id: initialMovie ? initialMovie.id : null,
      name: movie.nameValue,
      duration: +movie.durationValue,
      rating: +movie.ratingValue,
      genres: movie.genreList,
      posterImage: movie.posterImage,
    };
    console.log(newMovie);
    onSubmit(newMovie);

    //TODO : Reset the form somehow
    // setMovie({
    //     ...createInitialMovieState(initialMovie),
    //     genreList: [],
    // });
  };

  const isEditMode = Boolean(initialMovie);
  const buttonLabel = isEditMode ? "Edit Movie" : "Add New Movie";

  console.log("Validation State:", validationState);

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
