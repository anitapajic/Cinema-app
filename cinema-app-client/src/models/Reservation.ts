import React from "react";
import { Seat } from "./Seat";
import ReservationStatus from "./Enums/ReservationStatus";

export interface NewReservation {
  userEmail: string;
  numOfTickets: number;
  totalPrice: number;
  selectedSeats: Seat[];
  screeningId: number;
}

export interface Reservation {
  id: number;
  tag: string;
  movieScreeningId: number;
  numOfTickets: number;
  reservationStatus: ReservationStatus;
  selectedSeats: Seat[];
  timestamp: Date;
  totalPrice: number;
  userEmail: string;
}
