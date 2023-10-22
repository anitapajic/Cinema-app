import { NewReservation } from "../../models/Reservation";
import customAxios from "../AxiosInterceptor/AxiosInterceptor";

class ReservationService {
  makeReservation(reservationData: NewReservation) {
    return customAxios.post(`/reservations`, reservationData);
  }
  getConsumersReservations(userEmail: string) {
    return customAxios.get(`/reservations/${userEmail}`);
  }
  cancelReservation(id: number) {
    return customAxios.get(`/reservations/cancel/${id}`);
  }
}

export default new ReservationService();
