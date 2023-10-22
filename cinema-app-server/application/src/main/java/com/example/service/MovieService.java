package com.example.service;

import com.example.mapper.genre.GenreMapperApplication;
import com.example.mapper.movie.MovieMapperApplication;
import com.example.model.Genre;
import com.example.model.Movie;
import com.example.ports.input.genre.find_all_genres_usecase.FindAllGenresUsecase;
import com.example.ports.input.genre.find_by_id_usecase.FindGenreByIdResponse;
import com.example.ports.input.genre.update_genre_usecase.UpdateGenreRequest;
import com.example.ports.input.genre.update_genre_usecase.UpdateGenreResponse;
import com.example.ports.input.movie.delete_movie_usecase.DeleteMovieUsecase;
import com.example.ports.input.movie.find_all_movies_usecase.FindAllMoviesResponse;
import com.example.ports.input.movie.find_all_movies_usecase.FindAllMoviesUsecase;
import com.example.ports.input.movie.find_by_id_usecase.FindMovieByIdResponse;
import com.example.ports.input.movie.find_by_id_usecase.FindMovieByIdUsecase;
import com.example.ports.input.movie.save_movie_usecase.SaveMovieRequest;
import com.example.ports.input.movie.save_movie_usecase.SaveMovieResponse;
import com.example.ports.input.movie.save_movie_usecase.SaveMovieUsecase;
import com.example.ports.input.movie.update_movie_usecase.UpdateMovieRequest;
import com.example.ports.input.movie.update_movie_usecase.UpdateMovieResponse;
import com.example.ports.input.movie.update_movie_usecase.UpdateMovieUsecase;
import com.example.ports.output.movie.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieService implements SaveMovieUsecase,
                                     FindMovieByIdUsecase,
                                     FindAllMoviesUsecase,
                                     DeleteMovieUsecase,
                                     UpdateMovieUsecase {
    private final SaveMoviePort saveMoviePort;
    private final FindMovieByIdPort findMovieByIdPort;
    private final FindAllMoviesPort findAllMoviesPort;
    private final DeleteMoviePort deleteMoviePort;
    private final UpdateMoviePort updateMoviePort;
    @Override
    public SaveMovieResponse save(SaveMovieRequest saveMovieRequest) {
        Movie movie = MovieMapperApplication.toDomain(saveMovieRequest);
        Movie savedMovie = saveMoviePort.save(movie);
        return MovieMapperApplication.toResponse(savedMovie);
    }

    @Override
    public FindMovieByIdResponse findById(Long id){
        Movie movie = findMovieByIdPort.findById(id);
        return MovieMapperApplication.toResponseFindById(movie);
    }

    @Override
    public List<FindAllMoviesResponse> findAll(){
        return findAllMoviesPort.findAll().stream()
                .map(MovieMapperApplication::toResponseFindAll)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id){
        deleteMoviePort.deleteById(id);
    }

    @Override
    public UpdateMovieResponse update(Long id, UpdateMovieRequest updateMovieRequest){
        Movie movie = MovieMapperApplication.toDomainUpdate(updateMovieRequest);
        Movie updatedMovie = updateMoviePort.update(id, movie);
        return MovieMapperApplication.toResponseUpdate(updatedMovie);
    }
}
