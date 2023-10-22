package com.example.mapper.movie;

import com.example.model.Genre;
import com.example.model.Movie;
import com.example.ports.input.genre.find_by_id_usecase.FindGenreByIdResponse;
import com.example.ports.input.movie.find_all_movies_usecase.FindAllMoviesResponse;
import com.example.ports.input.movie.find_by_id_usecase.FindMovieByIdResponse;
import com.example.ports.input.movie.save_movie_usecase.SaveMovieRequest;
import com.example.ports.input.movie.save_movie_usecase.SaveMovieResponse;
import com.example.ports.input.movie.update_movie_usecase.UpdateMovieRequest;
import com.example.ports.input.movie.update_movie_usecase.UpdateMovieResponse;

public class MovieMapperApplication {
    public static Movie toDomain(SaveMovieRequest saveMovieRequest) {
        return new Movie(saveMovieRequest.getName(),
                         saveMovieRequest.getDuration(),
                         saveMovieRequest.getRating(),
                         saveMovieRequest.getPosterImage(),
                         saveMovieRequest.getGenres());
    }

    public static SaveMovieResponse toResponse(Movie movie) {
        return new SaveMovieResponse(movie.getId(),
                                     movie.getName(),
                                     movie.getDuration(),
                                     movie.getRating(),
                                     movie.getPosterImage(),
                                     movie.getGenres());
    }

    public static FindMovieByIdResponse toResponseFindById(Movie movie) {
        return new FindMovieByIdResponse(movie.getId(),
                                         movie.getName(),
                                         movie.getDuration(),
                                         movie.getRating(),
                                         movie.getPosterImage(),
                                         movie.getGenres());
    }

    public static FindAllMoviesResponse toResponseFindAll(Movie movie) {
        return new FindAllMoviesResponse(movie.getId(),
                                        movie.getName(),
                                        movie.getDuration(),
                                        movie.getRating(),
                                        movie.getPosterImage(),
                                        movie.getGenres());
    }

    public static UpdateMovieResponse toResponseUpdate(Movie movie){
        return new UpdateMovieResponse(movie.getId(),
                                       movie.getName(),
                                       movie.getDuration(),
                                       movie.getRating(),
                                       movie.getPosterImage(),
                                       movie.getGenres());
    }
    public static Movie toDomainUpdate(UpdateMovieRequest updateMovieRequest){
        return new Movie(updateMovieRequest.getName(),
                updateMovieRequest.getDuration(),
                updateMovieRequest.getRating(),
                updateMovieRequest.getPosterImage(),
                updateMovieRequest.getGenres());
    }
}
