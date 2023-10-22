import { faEdit, faTrash } from "@fortawesome/free-solid-svg-icons";
import MovieScreening from "../../../models/MovieScreenings";
import { IconButtonType } from "../../../utils/enums";
import IconButton from "../../shared/iconButton/IconButton";
import {
  ButtonContainer,
  Card,
  GenreItem,
  GenreList,
  GenresSection,
} from "../../shared/styled/SharedStyles.styled";
import {
  formatDate,
  formatTime,
} from "../../../utils/functions/formatDateTime";
import PosterImage from "../../shared/posterImage/PosterImage";

export type ScreeningCardProps = {
  screening: MovieScreening;
  onEdit: () => void;
  onDelete: () => void;
};

export default function ScreeningCard({
  screening,
  onEdit,
  onDelete,
}: ScreeningCardProps) {
  return (
    <>
      <Card>
        <PosterImage movie={screening.movie} />
        <h3>{screening.movie.name}</h3>
        <p>
          <b>Date: {formatDate(screening.dateTime)}</b>
        </p>
        <p>
          <b>Time: {formatTime(screening.dateTime)}</b>
        </p>
        <p>
          <b>Price:</b> {screening.price} RSD
        </p>
        <p>
          <b>Duration:</b> {screening.movie.duration} minutes
        </p>
        <p>
          <b>Rating:</b> {screening.movie.rating}
        </p>
        <GenresSection>
          <b>Genres:</b>
          <GenreList>
            {screening.movie.genres.map((genre) => (
              <GenreItem key={genre.id}>{genre.type}</GenreItem>
            ))}
          </GenreList>
        </GenresSection>
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
      </Card>
    </>
  );
}
