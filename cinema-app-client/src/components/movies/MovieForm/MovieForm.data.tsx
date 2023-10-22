import Movie from "../../../models/Movie";
import { MovieState } from "./MovieForm";

export const createInitialMovieState = (initialMovie: Movie | null): MovieState => ({
    nameValue: initialMovie ? initialMovie.name : "",
    durationValue: initialMovie ? initialMovie.duration.toString() : "",
    ratingValue: initialMovie ? initialMovie.rating.toString() : "",
    selectedGenre: "",
    genreList: initialMovie ? initialMovie.genres : [],
    posterImage: initialMovie ? initialMovie.posterImage : null,
});
