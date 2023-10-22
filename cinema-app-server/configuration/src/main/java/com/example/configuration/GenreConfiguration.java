package com.example.configuration;

import com.example.persistence.genre.adapters.*;
import com.example.persistence.genre.repository.GenreRepository;
import com.example.ports.input.genre.delete_genre_usecase.DeleteGenreUsecase;
import com.example.ports.input.genre.find_all_genres_usecase.FindAllGenresUsecase;
import com.example.ports.input.genre.find_by_id_usecase.FindGenreByIdUsecase;
import com.example.ports.input.genre.find_by_type_usecase.FindGenreByTypeUsecase;
import com.example.ports.input.genre.save_genre_usecase.SaveGenreUsecase;
import com.example.ports.input.genre.update_genre_usecase.UpdateGenreUsecase;
import com.example.ports.output.genre.*;
import com.example.service.GenreService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenreConfiguration {

    //======================================================================================
    // PORTS
    //======================================================================================

    @Bean
    public SaveGenrePort saveGenrePort(GenreRepository genreRepository){
        return new SaveGenreAdapter(genreRepository);
    }

    @Bean
    public FindAllGenresPort findAllGenresPort(GenreRepository genreRepository){
        return new FindAllGenresAdapter(genreRepository);
    }
    @Bean
    public FindGenreByIdPort findGenreByIdPort(GenreRepository genreRepository){
        return new FindGenreByIdAdapter(genreRepository);
    }
    @Bean
    public DeleteGenrePort deleteGenrePort(GenreRepository genreRepository){
        return new DeleteGenreAdapter(genreRepository);
    }
    @Bean
    public UpdateGenrePort updateGenrePort(GenreRepository genreRepository){
        return new UpdateGenreAdapter(genreRepository);
    }

    //======================================================================================
    // USE CASES
    //======================================================================================

    @Bean
    public SaveGenreUsecase saveGenreUsecase(SaveGenrePort saveGenrePort, FindAllGenresPort findAllGenresPort, FindGenreByIdPort findGenreByIdPort, DeleteGenrePort deleteGenrePort, UpdateGenrePort updateGenrePort, FindGenreByTypePort findGenreByTypePort){
        return new GenreService(saveGenrePort, findAllGenresPort, findGenreByIdPort, deleteGenrePort, updateGenrePort, findGenreByTypePort);
    }
    @Bean
    public FindAllGenresUsecase findAllGenresUsecase(SaveGenrePort saveGenrePort, FindAllGenresPort findAllGenresPort, FindGenreByIdPort findGenreByIdPort, DeleteGenrePort deleteGenrePort, UpdateGenrePort updateGenrePort, FindGenreByTypePort findGenreByTypePort){
        return new GenreService(saveGenrePort, findAllGenresPort, findGenreByIdPort, deleteGenrePort, updateGenrePort, findGenreByTypePort);
    }
    @Bean
    FindGenreByIdUsecase findGenreByIdUsecase(SaveGenrePort saveGenrePort, FindAllGenresPort findAllGenresPort, FindGenreByIdPort findGenreByIdPort, DeleteGenrePort deleteGenrePort, UpdateGenrePort updateGenrePort, FindGenreByTypePort findGenreByTypePort){
        return new GenreService(saveGenrePort, findAllGenresPort, findGenreByIdPort, deleteGenrePort, updateGenrePort, findGenreByTypePort);
    }

    @Bean
    FindGenreByTypeUsecase findGenreByTypeUsecase(SaveGenrePort saveGenrePort, FindAllGenresPort findAllGenresPort, FindGenreByIdPort findGenreByIdPort, DeleteGenrePort deleteGenrePort, UpdateGenrePort updateGenrePort, FindGenreByTypePort findGenreByTypePort){
        return new GenreService(saveGenrePort, findAllGenresPort, findGenreByIdPort, deleteGenrePort, updateGenrePort, findGenreByTypePort);
    }

    @Bean
    DeleteGenreUsecase deleteGenreUsecase(SaveGenrePort saveGenrePort, FindAllGenresPort findAllGenresPort, FindGenreByIdPort findGenreByIdPort, DeleteGenrePort deleteGenrePort, UpdateGenrePort updateGenrePort, FindGenreByTypePort findGenreByTypePort){
        return new GenreService(saveGenrePort, findAllGenresPort, findGenreByIdPort, deleteGenrePort, updateGenrePort, findGenreByTypePort);
    }

    @Bean
    UpdateGenreUsecase updateGenreUsecase(SaveGenrePort saveGenrePort, FindAllGenresPort findAllGenresPort, FindGenreByIdPort findGenreByIdPort, DeleteGenrePort deleteGenrePort, UpdateGenrePort updateGenrePort, FindGenreByTypePort findGenreByTypePort){
        return new GenreService(saveGenrePort, findAllGenresPort, findGenreByIdPort, deleteGenrePort, updateGenrePort, findGenreByTypePort);
    }
}
