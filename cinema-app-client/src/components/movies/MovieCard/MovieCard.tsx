import { faEdit, faTrash } from "@fortawesome/free-solid-svg-icons";
import Movie from "../../../models/Movie";
import IconButton from "../../shared/iconButton/IconButton";
import {
  ButtonContainer,
  Card,
  GenreItem,
  GenreList,
  GenresSection,
  ScreeningItem,
  ScreeningList,
  ScreeningsSection,
} from "../../shared/styled/SharedStyles.styled";
import { ButtonSize, ButtonType, IconButtonType } from "../../../utils/enums";
import { NewMovieScreening } from "../../../models/MovieScreenings";
import { formatTime } from "../../../utils/functions/formatDateTime";
import Button from "../../shared/button/Button";
import { Link } from "react-router-dom";
import PosterImage from "../../shared/posterImage/PosterImage";

export type MovieCardProps = {
  movie: Movie;
  onEdit: () => void;
  onDelete: () => void;
  showScreenings?: boolean;
  screenings?: { [key: number]: NewMovieScreening[] };
};

export default function MovieCard({
  movie,
  onEdit,
  onDelete,
  showScreenings = false,
  screenings,
}: MovieCardProps) {
  const movieScreenings =
    movie.id !== null && screenings ? screenings[movie.id] : [];

  return (
    <Card>
      <PosterImage movie={movie} />
      <h3>{movie.name}</h3>
      {movieScreenings && movieScreenings.length > 0 && (
        <ScreeningsSection>
          <b>Screenings:</b>
          <ScreeningList>
            {movieScreenings.map((screening: NewMovieScreening) => (
              <Link to={`/reservations/${screening.id}`}>
                <Button
                  label={formatTime(screening.dateTime)}
                  variant={ButtonType.PRIMARY}
                  size={ButtonSize.SMALL}
                />
              </Link>
            ))}
          </ScreeningList>
        </ScreeningsSection>
      )}
      <p>
        <b>Duration:</b> {movie.duration} minutes
      </p>
      <p>
        <b>Rating:</b> {movie.rating}
      </p>
      <GenresSection>
        <b>Genres:</b>
        <GenreList>
          {movie.genres.map((genre) => {
            return <GenreItem key={genre.type}>{genre.type}</GenreItem>;
          })}
        </GenreList>
      </GenresSection>

      {!showScreenings && (
        <ButtonContainer>
          <IconButton
            icon={faEdit}
            onClickHandler={onEdit}
            variant={IconButtonType.WHITE}
          />
          <IconButton
            icon={faTrash}
            onClickHandler={onDelete}
            variant={IconButtonType.WHITE}
          />
        </ButtonContainer>
      )}
    </Card>
  );
}
