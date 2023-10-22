import Genre from "./Genre";
import MovieScreening, { NewMovieScreening } from "./MovieScreenings";

interface Movie {
  id: number | null;
  name: string;
  duration: number;
  rating: number;
  posterImage: string | null;
  genres: Genre[];
  screenings?: NewMovieScreening[];
}

export default Movie;
