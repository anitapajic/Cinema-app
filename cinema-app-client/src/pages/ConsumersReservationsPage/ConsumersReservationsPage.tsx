import { Tabs, TabList, Tab, TabPanel } from "react-tabs";
import { StyledPage } from "../../components/shared/styled/SharedStyles.styled";
import { useEffect, useState } from "react";
import ReservationService from "../../services/ReservationService/ReservationService";
import useUser from "../../utils/UserContext/useUser";
import { Reservation } from "../../models/Reservation";
import {
  StyledTabs,
  StyledTabList,
  StyledTab,
  StyledTabPanel,
} from "./ConsumersReservationsPage.styled";
import ReservationsList from "../../components/reservations/ReservationsList/ReservationsList";
import ScreeningService from "../../services/ScreeningService/ScreeningService";
import MovieScreening from "../../models/MovieScreenings";
import { showToast } from "../../components/shared/toast/CustomToast";
import ConfirmationForm from "../../components/shared/confirmationForm/ConfirmationForm";
import Modal from "../../components/shared/modal/Modal";
import ReservationStatus from "../../models/Enums/ReservationStatus";

export default function ConsumersReservationsPage() {
  const [reservations, setReservations] = useState<Reservation[]>([]);
  const [pastReservations, setPastReservations] = useState<Reservation[]>([]);
  const [upcomingReservations, setUpcomingReservations] = useState<
    Reservation[]
  >([]);
  const [tabOpened, setTabOpened] = useState(0);
  const { user } = useUser();
  const [isConfirmDialogVisible, setIsConfirmDialogVisible] = useState(false);
  const [reservationToCancel, setReservationToCancel] =
    useState<Reservation | null>(null);

  useEffect(() => {
    if (user != null) {
      ReservationService.getConsumersReservations(user?.username)
        .then(async (response) => {
          const allReservations: Reservation[] = response.data;
          const nonCancelledReservations = allReservations.filter(
            (res) => res.reservationStatus !== ReservationStatus.CANCELED
          );

          const screenings: MovieScreening[] = await Promise.all(
            nonCancelledReservations.map(async (r: Reservation) => {
              const response = await ScreeningService.getScreeningById(
                r.movieScreeningId
              );
              return response.data;
            })
          );

          const now = new Date();

          const past = nonCancelledReservations.filter(
            (r: Reservation, index: number) => {
              return new Date(screenings[index].dateTime) <= now;
            }
          );

          const upcoming = nonCancelledReservations.filter(
            (r: Reservation, index: number) => {
              return new Date(screenings[index].dateTime) > now;
            }
          );

          setReservations(nonCancelledReservations);
          setPastReservations(past);
          setUpcomingReservations(upcoming);
        })
        .catch((error) => {
          console.error("Error fetching reservations or screenings: ", error);
        });
    }
  }, []);

  const onCancel = (reservation: Reservation) => {
    setReservationToCancel(reservation);
    setIsConfirmDialogVisible(true);
  };

  const handleDialogCancel = () => {
    setIsConfirmDialogVisible(false);
  };

  const onConfirmCancel = () => {
    if (!reservationToCancel) return;

    ReservationService.cancelReservation(reservationToCancel.id)
      .then(() => {
        const updatedReservations = reservations.filter(
          (res) => res.id !== reservationToCancel.id
        );
        setReservations(updatedReservations);
        setReservationToCancel(null);
        setIsConfirmDialogVisible(false);
        console.log(reservationToCancel);
        showToast(
          `Successfully cancelled reservation ${reservationToCancel.id}`
        );
      })
      .catch((error) => {
        console.error("Error canceling reservation: ", error);
        showToast(`Error canceling reservation: ${reservationToCancel.id}`);
      });
    console.log(reservationToCancel);
  };

  return (
    <StyledPage>
      <h2>My reservations</h2>
      <StyledTabs
        selectedIndex={tabOpened}
        onSelect={(index) => setTabOpened(index)}
      >
        <StyledTabList>
          <StyledTab>Upcoming Reservations</StyledTab>
          <StyledTab>Past Reservations</StyledTab>
        </StyledTabList>

        <StyledTabPanel>
          <ReservationsList
            reservations={upcomingReservations}
            onCancel={onCancel}
            showButtonCancel={true}
          />
        </StyledTabPanel>

        <StyledTabPanel>
          <ReservationsList
            reservations={pastReservations}
            showButtonCancel={false}
            onCancel={() => {}}
          />
        </StyledTabPanel>
      </StyledTabs>
      <Modal isVisible={isConfirmDialogVisible} onClose={handleDialogCancel}>
        <ConfirmationForm
          label="Are you sure you want to continue with canceling this reservation?"
          confirmLabel="CONTINUE"
          onCancel={handleDialogCancel}
          onConfirm={onConfirmCancel}
        />
      </Modal>
    </StyledPage>
  );
}
