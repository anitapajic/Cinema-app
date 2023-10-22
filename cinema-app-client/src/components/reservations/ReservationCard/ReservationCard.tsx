import React, { useEffect, useState } from "react";
import { Reservation } from "../../../models/Reservation";
import { ButtonType, ButtonSize } from "../../../utils/enums";
import {
  formatDate,
  formatTime,
} from "../../../utils/functions/formatDateTime";
import { Card } from "../../genres/GenreCard/GenreCard.styled";
import Button from "../../shared/button/Button";
import MovieScreening from "../../../models/MovieScreenings";
import ScreeningService from "../../../services/ScreeningService/ScreeningService";
import PosterImage from "../../shared/posterImage/PosterImage";

export type ReservationCardProps = {
  reservation: Reservation;
  onCancel: (reservation: Reservation) => void;
  showButtonCancel: boolean;
};
export default function ReservationCard({
  reservation,
  onCancel,
  showButtonCancel,
}: ReservationCardProps) {
  const [screening, setScreening] = useState<MovieScreening | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const screeningResponse = await ScreeningService.getScreeningById(
          Number(reservation.movieScreeningId)
        );
        if (screeningResponse.data) {
          setScreening(screeningResponse.data);
        }
      } catch (error) {
        console.error("Failed to fetch data:", error);
      }
    };

    fetchData();
  }, [reservation.movieScreeningId]);
  return (
    <Card>
      <h3>{screening?.movie.name}</h3>
      <PosterImage movie={screening?.movie} />
      <p>
        <b>Reservation ID: </b>
        {reservation.tag}
      </p>
      <p>
        {screening && (
          <>
            <b>Movie Date: </b> {formatDate(screening.dateTime)}
          </>
        )}
      </p>
      <p>
        {screening && (
          <>
            <b>Movie Time: </b> {formatTime(screening.dateTime)}
          </>
        )}
      </p>
      <p>
        <b>Number of tickets: </b>
        {reservation.numOfTickets}
      </p>
      <p></p>
      <p>
        <b>Total price: </b>
        {reservation.totalPrice} RSD
      </p>
      <p>
        <b>Selected Seats:</b>
      </p>
      <ul>
        {reservation.selectedSeats.map((seat) => (
          <li key={seat.id}>
            Row: {seat.row}, Number: {seat.col}
          </li>
        ))}
      </ul>

      {showButtonCancel && (
        <Button
          label="CANCEL"
          variant={ButtonType.PRIMARY}
          size={ButtonSize.STANDARD}
          onClickHandler={(event) => onCancel(reservation)}
        />
      )}
    </Card>
  );
}
