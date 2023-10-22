import Movie from "../../models/Movie";
import customAxios from "../AxiosInterceptor/AxiosInterceptor";

class MovieService {
  //Get all movies
  getMovies() {
    return customAxios.get(`/movies/all`);
  }
  // Get a movie by id
  getMovieById(id: number) {
    return customAxios.get(`/movies/${id}`);
  }

  // Create a new movie
  createMovie(movieData: Movie) {
    return customAxios.post(`/movies`, movieData);
  }

  // Update a movie
  updateMovie(id: number, movieData: Movie) {
    return customAxios.put(`/movies/${id}`, movieData);
  }

  // Delete a movie
  deleteMovie(id: number) {
    return customAxios.delete(`/movies/${id}`);
  }
}
export default new MovieService();
