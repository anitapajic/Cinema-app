import { Routes, Route } from "react-router-dom";

import GenresPage from "../pages/GenresPage/GenresPage";
import MoviesPage from "../pages/MoviesPage/MoviesPage";
import HomePage from "../pages/HomePage/HomePage";
import ScreeningsPage from "../pages/ScreeningsPage/ScreeningsPage";
import ReservationPage from "../pages/ReservationPage/ReservationPage";
import LoginPage from "../pages/LoginPage/LoginPage";
import ConsumersPage from "../pages/ConsumersPage/ConsumersPage";
import ConsumersReservationsPage from "../pages/ConsumersReservationsPage/ConsumersReservationsPage";

export default function MyRoutes() {
  return (
    <Routes>
      <Route path="/genres" element={<GenresPage />} />
      <Route path="/movies" element={<MoviesPage />} />
      <Route path="" element={<HomePage />} />
      <Route path="/screenings" element={<ScreeningsPage />} />
      <Route path="/reservations/:screeningId" element={<ReservationPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/consumers" element={<ConsumersPage />} />
      <Route path="/my-reservations" element={<ConsumersReservationsPage />} />
    </Routes>
  );
}
