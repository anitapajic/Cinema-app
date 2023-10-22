import { useEffect, useState } from "react";
import Movie from "../../models/Movie";
import MovieService from "../../services/MovieService/MovieService";
import MoviesList from "../../components/movies/MoviesList/MoviesList";
import {
  HeaderWrapper,
  StyledInput,
  StyledPage,
} from "../../components/shared/styled/SharedStyles.styled";
import ScreeningService from "../../services/ScreeningService/ScreeningService";
import { NewMovieScreening } from "../../models/MovieScreenings";
import { addDays, format } from "date-fns";
import GenreService from "../../services/GenreService/GenreService";
import Genre from "../../models/Genre";
import { SelectInput, SelectWrapper } from "./HomePage.styled";

export default function HomePage() {
  const [moviesWithScreenings, setMoviesWithScreenings] = useState<Movie[]>([]);
  const [selectedDate, setSelectedDate] = useState<Date>(new Date());
  const [screenings, setScreenings] = useState<{
    [key: number]: NewMovieScreening[];
  }>({});
  const [selectedGenre, setSelectedGenre] = useState<string | null>(null);
  const [genres, setGenres] = useState<Genre[]>([]);
  const [sortingPreference, setSortingPreference] = useState<
    "date" | "alphabetical"
  >("date");

  const minDate = format(new Date(), "yyyy-MM-dd");
  const maxDate = format(addDays(new Date(), 7), "yyyy-MM-dd");
  const currentDate = new Date();

  const handleDateChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const newDate = new Date(event.target.value);
    setSelectedDate(newDate);
  };

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

  useEffect(() => {
    const formattedDate = selectedDate.toISOString().split("T")[0];

    const fetchScreeningsAndMovies = async () => {
      try {
        const [screeningsResponse, moviesResponse] = await Promise.all([
          ScreeningService.getScreeningsByDate(formattedDate),
          MovieService.getMovies(),
        ]);

        const screeningsByDate: NewMovieScreening[] = screeningsResponse.data;

        const screeningsMap: { [key: number]: NewMovieScreening[] } =
          screeningsByDate
            .filter(
              (screening: NewMovieScreening) =>
                screening.movieId !== null &&
                new Date(screening.dateTime) > currentDate
            )
            .reduce(
              (
                acc: { [key: number]: NewMovieScreening[] },
                screening: NewMovieScreening
              ) => {
                if (!acc[screening.movieId!]) {
                  acc[screening.movieId!] = [];
                }
                acc[screening.movieId!].push(screening);
                return acc;
              },
              {}
            );

        setScreenings(screeningsMap);

        const moviesList: Movie[] = moviesResponse.data;

        const filteredMoviesWithScreenings: Movie[] = moviesList.filter(
          (movie: Movie) =>
            movie.id !== null &&
            screeningsMap[movie.id]?.length > 0 &&
            (!selectedGenre ||
              movie.genres.some((genre) => genre.type === selectedGenre))
        );
        const sortedMovies = sortMovies(filteredMoviesWithScreenings);
        setMoviesWithScreenings(sortedMovies);
      } catch (error) {
        console.error("Error fetching data: ", error);
      }
    };

    fetchScreeningsAndMovies();
  }, [selectedDate, selectedGenre, sortingPreference]);

  function sortMovies(movies: Movie[]): Movie[] {
    if (sortingPreference === "date") {
      return movies.sort((a, b) => {
        const aScreeningDate = screenings[a.id!]?.[0]?.dateTime;
        const bScreeningDate = screenings[b.id!]?.[0]?.dateTime;

        if (aScreeningDate && bScreeningDate) {
          return (
            new Date(aScreeningDate).getTime() -
            new Date(bScreeningDate).getTime()
          );
        }
        return 0;
      });
    } else {
      return movies.sort((a, b) => a.name.localeCompare(b.name));
    }
  }

  return (
    <StyledPage>
      <HeaderWrapper>
        <h2>Cinema repertoire</h2>
        <StyledInput
          type="date"
          value={selectedDate.toISOString().split("T")[0]}
          onChange={handleDateChange}
          min={minDate}
          max={maxDate}
          isValid={true}
        />
        <SelectWrapper>
          <SelectInput onChange={(e) => setSelectedGenre(e.target.value)}>
            <option value="">All Genres</option>
            {genres.map((genre) => (
              <option key={genre.type} value={genre.type}>
                {genre.type}
              </option>
            ))}
          </SelectInput>
          <SelectInput
            value={sortingPreference}
            onChange={(e) =>
              setSortingPreference(e.target.value as "date" | "alphabetical")
            }
          >
            <option value="date">Sort by Date</option>
            <option value="alphabetical">Sort Alphabetically</option>
          </SelectInput>
        </SelectWrapper>
      </HeaderWrapper>
      <MoviesList
        movies={moviesWithScreenings}
        showScreenings={true}
        screenings={screenings}
      />
    </StyledPage>
  );
}
