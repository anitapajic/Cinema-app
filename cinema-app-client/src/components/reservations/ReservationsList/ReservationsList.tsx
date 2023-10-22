import { Reservation } from "../../../models/Reservation";
import { ItemsListStyle } from "../../shared/styled/SharedStyles.styled";
import ReservationCard from "../ReservationCard/ReservationCard";

export type ReservationsListProps = {
  reservations: Reservation[];
  onCancel: (reservation: Reservation) => void;
  showButtonCancel: boolean;
};

export default function ReservationsList({
  reservations,
  onCancel,
  showButtonCancel,
}: ReservationsListProps) {
  return (
    <ItemsListStyle>
      {reservations.map((reservation: Reservation) => (
        <ReservationCard
          key={reservation.id}
          reservation={reservation}
          onCancel={onCancel}
          showButtonCancel={showButtonCancel}
        />
      ))}
    </ItemsListStyle>
  );
}
