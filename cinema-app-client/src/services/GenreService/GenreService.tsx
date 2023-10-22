import axios from "axios";
import Genre from "../../models/Genre";
import customAxios from "../AxiosInterceptor/AxiosInterceptor";

class GenreService {
  // Get all genres
  getGenres() {
    return customAxios.get(`/genres/all`);
  }

  // Get a genre by id
  getGenreById(id: number) {
    return customAxios.get(`/genres/${id}`);
  }

  // Create a new genre
  createGenre(genreData: Genre) {
    return customAxios.post(`/genres`, genreData);
  }

  // Update a genre
  updateGenre(id: number, genreData: Genre) {
    return customAxios.put(`/genres/${id}`, genreData);
  }

  // Delete a genre
  deleteGenre(id: number) {
    return customAxios.delete(`/genres/${id}`);
  }
}

export default new GenreService();
