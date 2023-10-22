package com.example.configuration;

import com.example.persistence.genre.repository.GenreRepository;
import com.example.persistence.movie.adapters.*;
import com.example.persistence.movie.repository.MovieRepository;
import com.example.ports.input.movie.delete_movie_usecase.DeleteMovieUsecase;
import com.example.ports.input.movie.find_all_movies_usecase.FindAllMoviesUsecase;
import com.example.ports.input.movie.find_by_id_usecase.FindMovieByIdUsecase;
import com.example.ports.input.movie.save_movie_usecase.SaveMovieUsecase;
import com.example.ports.input.movie.update_movie_usecase.UpdateMovieUsecase;
import com.example.ports.output.movie.*;
import com.example.service.MovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieConfiguration {

    //======================================================================================
    // PORTS
    //======================================================================================

    @Bean
    public SaveMoviePort saveMoviePort(MovieRepository movieRepository){
        return new SaveMovieAdapter(movieRepository);
    }

    @Bean
    public FindMovieByIdPort findMovieByIdPort(MovieRepository movieRepository){
        return new FindMovieByIdAdapter(movieRepository);
    }
    @Bean
    public FindAllMoviesPort findAllMoviesPort(MovieRepository movieRepository){
        return new FindAllMoviesAdapter(movieRepository);
    }
    @Bean
    public DeleteMoviePort deleteMoviePort(MovieRepository movieRepository){
        return new DeleteMovieAdapter(movieRepository);
    }
    @Bean
    public UpdateMoviePort updateMoviePort(MovieRepository movieRepository, GenreRepository genreRepository){
        return new UpdateMovieAdapter(movieRepository, genreRepository);
    }

    //======================================================================================
    // USE CASES
    //======================================================================================

    @Bean
    public SaveMovieUsecase saveMovieUsecase(SaveMoviePort saveMoviePort, FindMovieByIdPort findMovieByIdPort, FindAllMoviesPort findAllMoviesPort, DeleteMoviePort deleteMoviePort, UpdateMoviePort updateMoviePort){
        return new MovieService(saveMoviePort, findMovieByIdPort, findAllMoviesPort, deleteMoviePort, updateMoviePort);
    }
    @Bean
    FindMovieByIdUsecase findMovieByIdUsecase(SaveMoviePort saveMoviePort, FindMovieByIdPort findMovieByIdPort, FindAllMoviesPort findAllMoviesPort, DeleteMoviePort deleteMoviePort, UpdateMoviePort updateMoviePort){
        return new MovieService(saveMoviePort, findMovieByIdPort, findAllMoviesPort, deleteMoviePort, updateMoviePort);
    }
    @Bean
    FindAllMoviesUsecase findAllMoviesUsecase(SaveMoviePort saveMoviePort, FindMovieByIdPort findMovieByIdPort, FindAllMoviesPort findAllMoviesPort, DeleteMoviePort deleteMoviePort, UpdateMoviePort updateMoviePort){
        return new MovieService(saveMoviePort, findMovieByIdPort, findAllMoviesPort, deleteMoviePort, updateMoviePort);
    }
    @Bean
    DeleteMovieUsecase deleteMovieUsecase(SaveMoviePort saveMoviePort, FindMovieByIdPort findMovieByIdPort, FindAllMoviesPort findAllMoviesPort, DeleteMoviePort deleteMoviePort, UpdateMoviePort updateMoviePort){
        return new MovieService(saveMoviePort, findMovieByIdPort, findAllMoviesPort, deleteMoviePort, updateMoviePort);
    }
    @Bean
    UpdateMovieUsecase updateMovieUsecase(SaveMoviePort saveMoviePort, FindMovieByIdPort findMovieByIdPort, FindAllMoviesPort findAllMoviesPort, DeleteMoviePort deleteMoviePort, UpdateMoviePort updateMoviePort){
        return new MovieService(saveMoviePort, findMovieByIdPort, findAllMoviesPort, deleteMoviePort, updateMoviePort);
    }
}
