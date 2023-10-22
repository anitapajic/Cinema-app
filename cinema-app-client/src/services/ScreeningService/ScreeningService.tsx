import MovieScreening, {
  NewMovieScreening,
} from "../../models/MovieScreenings";
import customAxios from "../AxiosInterceptor/AxiosInterceptor";

class ScreeningService {
  getScreenings() {
    return customAxios.get(`/screenings`);
  }

  getScreeningById(id: number) {
    return customAxios.get(`/screenings/${id}`);
  }

  createScreening(screeningData: NewMovieScreening) {
    return customAxios.post(`/screenings`, screeningData);
  }

  updateScreening(id: number, screeningData: NewMovieScreening) {
    return customAxios.put(`/screenings/${id}`, screeningData);
  }
  deleteScreening(id: number) {
    return customAxios.delete(`/screenings/${id}`);
  }
  getScreeningsByMovieId(movieId: number) {
    return customAxios.get(`/screenings/movie/${movieId}`);
  }
  getScreeningsByDate(date: string) {
    return customAxios.get(`/screenings/date`, { params: { date } });
  }
}

export default new ScreeningService();
