import Movie from "../../../models/Movie";
import { NewMovieScreening } from "../../../models/MovieScreenings";
import { ItemsListStyle } from "../../shared/styled/SharedStyles.styled";
import MovieCard from "../MovieCard/MovieCard";

export type MoviesListProps = {
  movies: Movie[];
  onEditMovie?: (movie: Movie) => void;
  onDeleteMovie?: (movie: Movie) => void;
  showScreenings?: boolean;
  screenings?: { [key: number]: NewMovieScreening[] };
};

export default function MoviesList({
  movies,
  onEditMovie,
  onDeleteMovie,
  showScreenings,
  screenings,
}: MoviesListProps) {
  return (
    <ItemsListStyle>
      {movies.map((movie: Movie) => (
        <MovieCard
          key={movie.id}
          movie={movie}
          onEdit={() => onEditMovie?.(movie)}
          onDelete={() => onDeleteMovie?.(movie)}
          showScreenings={showScreenings}
          screenings={screenings}
        />
      ))}
    </ItemsListStyle>
  );
}
