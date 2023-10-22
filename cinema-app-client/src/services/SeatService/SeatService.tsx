import customAxios from "../AxiosInterceptor/AxiosInterceptor";

class SeatService {
  getSeatsByScreeningId(id: number) {
    return customAxios.get(`/seats/screening/${id}`);
  }
}
export default new SeatService();
