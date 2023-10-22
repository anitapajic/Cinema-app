import MovieScreening from "../../../models/MovieScreenings";
import { ScreeningState } from "./ScreeningForm";

export const createInitialScreeningState = (initialScreening : MovieScreening | null) : ScreeningState => ({
    movieIdValue: initialScreening ? initialScreening.movie.id : null,
    dateTimeValue: initialScreening ? initialScreening.dateTime : "",
    priceValue: initialScreening ? initialScreening.price : "",
    seatColValue : initialScreening ? initialScreening.seatCol : "",
    seatRowValue : initialScreening ? initialScreening.seatRow : "",
})