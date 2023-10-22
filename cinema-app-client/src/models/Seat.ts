import React from "react";
import SeatStatus from "./Enums/SeatStatus";
export interface Seat {
  id: number;
  seatStatus: SeatStatus;
  row: number;
  col: number;
  screeningId: number;
}
