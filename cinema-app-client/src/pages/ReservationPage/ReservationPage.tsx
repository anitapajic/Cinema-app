import React, { useEffect, useState } from "react";
import {
  GenreItem,
  GenreList,
  GenresSection,
  HeaderWrapper,
  StyledInput,
  StyledLabel,
  StyledPage,
} from "../../components/shared/styled/SharedStyles.styled";
import { useParams } from "react-router-dom";
import MovieScreening from "../../models/MovieScreenings";
import ScreeningService from "../../services/ScreeningService/ScreeningService";
import {
  Container,
  ImageSection,
  PriceContainer,
  SeatBox,
  SeatsContainer,
  SeatsGrid,
  TicketContainer,
} from "./ReservationPage.styled";
import SeatService from "../../services/SeatService/SeatService";
import { Seat } from "../../models/Seat";
import Button from "../../components/shared/button/Button";
import { ButtonSize, ButtonType } from "../../utils/enums";
import SeatStatus from "../../models/Enums/SeatStatus";
import { formatTime } from "../../utils/functions/formatDateTime";
import { NewReservation } from "../../models/Reservation";
import ReservationService from "../../services/ReservationService/ReservationService";
import { showToast } from "../../components/shared/toast/CustomToast";
import ConfirmationForm from "../../components/shared/confirmationForm/ConfirmationForm";
import Modal from "../../components/shared/modal/Modal";
import useUser from "../../utils/UserContext/useUser";
import PosterImage from "../../components/shared/posterImage/PosterImage";
import { ErrorPage } from "../ErrorPage/ErrorPage";

export default function ReservationPage() {
  //TODO : ADD ERROR PAGE FOR MANUALLY WRONG IDS (error boundary)
  const [isError, setIsError] = useState(false);
  const { screeningId } = useParams();
  const [screening, setScreening] = useState<MovieScreening | null>(null);
  const [seats, setSeats] = useState<Seat[] | null>(null);
  const [count, setCount] = useState(1);
  const [selectedSeats, setSelectedSeats] = useState<number[]>([]);
  const [totalPrice, setTotalPrice] = useState(0);
  const [email, setEmail] = useState<string>("");
  const [isConfirmDialogVisible, setIsConfirmDialogVisible] = useState(false);
  const [reservationToMake, setReservationToMake] =
    useState<NewReservation | null>(null);
  const { user } = useUser();
  const [emailIsValid, setEmailIsValid] = useState(true);

  const validateEmail = (email: string) => {
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    return emailPattern.test(email);
  };

  const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const emailValue = e.target.value;
    setEmail(emailValue);

    setEmailIsValid(validateEmail(emailValue));
  };
  const increment = () => {
    setCount((prevCount) => prevCount + 1);
  };

  const decrement = () => {
    if (count > 1) {
      setCount((prevCount) => prevCount - 1);
    }
  };
  const toggleSeatSelection = (seatId: number) => {
    console.log("Toggling seat with ID:", seatId);
    if (selectedSeats.includes(seatId)) {
      setSelectedSeats((prevSeats) => prevSeats.filter((id) => id !== seatId));
    } else if (selectedSeats.length < count) {
      setSelectedSeats((prevSeats) => [...prevSeats, seatId]);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const screeningResponse = await ScreeningService.getScreeningById(
          Number(screeningId)
        );
        const seatResponse = await SeatService.getSeatsByScreeningId(
          Number(screeningId)
        );

        if (screeningResponse.data) {
          setScreening(screeningResponse.data);

          const discountPrice = user ? 0.95 : 1;
          setTotalPrice(count * screeningResponse.data.price * discountPrice);
        }

        const seats: Seat[] = seatResponse.data;
        setSeats(seats);
      } catch (error) {
        console.error("Failed to fetch data:", error);
        setIsError(true);
      }
    };

    fetchData();

    if (user) {
      setEmail(user.username);
    }
  }, [count]);

  const handleReserveAction = () => {
    if (!user && !validateEmail(email)) {
      showToast("Please enter a valid email address.");
      return;
    }
    const selectedSeatObjects =
      seats?.filter((seat) => selectedSeats.includes(seat.id)) || [];

    const reservationData: NewReservation = {
      userEmail: email,
      screeningId: Number(screeningId),
      selectedSeats: selectedSeatObjects,
      totalPrice: totalPrice,
      numOfTickets: count,
    };
    console.log(selectedSeatObjects);
    setReservationToMake(reservationData);
    setIsConfirmDialogVisible(true);
  };

  const handleReservation = () => {
    if (!reservationToMake) return;

    ReservationService.makeReservation(reservationToMake)
      .then((response) => {
        if (response) {
          showToast("Reservation created.");
        }
      })
      .catch((error) => {
        console.error("Reservation failed due to an error:", error);
      });
  };

  const handleConfirm = () => {
    setIsConfirmDialogVisible(false);
    handleReservation();
  };

  const handleCancel = () => {
    setIsConfirmDialogVisible(false);
    setReservationToMake(null);
  };

  return isError ? (
    <ErrorPage />
  ) : (
    <StyledPage>
      <HeaderWrapper>
        <h2>
          Reservation for {screening?.movie.name} at
          {formatTime(screening?.dateTime)}h
        </h2>
      </HeaderWrapper>
      <Container>
        <ImageSection>
          <PosterImage movie={screening?.movie} />
          <StyledLabel>Duration: {screening?.movie.duration} min</StyledLabel>
          <StyledLabel>Rating: {screening?.movie.rating}</StyledLabel>
          <StyledLabel>Genres:</StyledLabel>
          <GenreList>
            {screening?.movie.genres.map((genre) => (
              <GenreItem key={genre.id}>{genre.type}</GenreItem>
            ))}
          </GenreList>
        </ImageSection>
        <SeatsContainer>
          <TicketContainer>
            <StyledLabel>Number of Tickets: </StyledLabel>
            <Button
              onClickHandler={decrement}
              label="-"
              size={ButtonSize.STANDARD}
              variant={ButtonType.PRIMARY}
            />
            <StyledInput
              type="number"
              value={count}
              onChange={(e) => setCount(Math.max(1, Number(e.target.value)))}
              isValid={true}
            />
            <Button
              onClickHandler={increment}
              label="+"
              size={ButtonSize.STANDARD}
              variant={ButtonType.PRIMARY}
            />
          </TicketContainer>
          <StyledLabel>Please select your seats: </StyledLabel>
          <SeatsGrid columns={screening?.seatCol}>
            {seats?.map((seat) => (
              <SeatBox
                key={seat.id}
                status={
                  selectedSeats.includes(seat.id)
                    ? SeatStatus.SELECTED
                    : seat.seatStatus
                }
                onClick={() => {
                  if (seat.seatStatus !== SeatStatus.OCCUPIED) {
                    toggleSeatSelection(seat.id);
                  }
                }}
              />
            ))}
          </SeatsGrid>
        </SeatsContainer>
        <PriceContainer>
          <StyledLabel>
            Total Price: {totalPrice ? totalPrice : "Calculating..."} RSD
          </StyledLabel>
          {!user ? (
            <StyledInput
              placeholder="Enter email address"
              type="email"
              isValid={emailIsValid}
              onChange={handleEmailChange}
              required
            />
          ) : null}
          <Button
            label="Reserve ticket"
            variant={ButtonType.SECONDARY}
            size={ButtonSize.LARGE}
            onClickHandler={handleReserveAction}
          />
        </PriceContainer>
      </Container>
      <Modal isVisible={isConfirmDialogVisible} onClose={handleCancel}>
        <ConfirmationForm
          label="Are you sure you want to make this reservation?"
          confirmLabel="Reserve"
          onCancel={handleCancel}
          onConfirm={handleConfirm}
        />
      </Modal>
    </StyledPage>
  );
}
