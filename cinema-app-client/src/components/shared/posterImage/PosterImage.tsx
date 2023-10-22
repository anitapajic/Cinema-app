import Movie from "../../../models/Movie";
import { Poster } from "./PosterImage.styled";

export type PosterImageProps = {
  movie: Movie | undefined;
};
export default function PosterImage({ movie }: PosterImageProps) {
  let src;

  if (typeof movie?.posterImage === "string") {
    src = movie.posterImage.startsWith("data:image")
      ? movie.posterImage
      : `${movie.posterImage}`;
  } else {
    src = movie?.posterImage
      ? URL.createObjectURL(movie.posterImage)
      : undefined;
  }

  return <Poster src={src} />;
}
