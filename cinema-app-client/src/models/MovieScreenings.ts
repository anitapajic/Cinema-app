import Movie from "./Movie";

export interface MovieScreening{
    id : number | null;
    dateTime : string;
    movie : Movie;
    price : number;
    seatCol : number;
    seatRow: number;

}
export interface NewMovieScreening {
    id: number | null;
    dateTime: string;
    price: number;
    seatCol: number;
    seatRow: number;
    movieId: number | null;
}
export default MovieScreening;