package com.example.web.controller.movie;

import com.example.ports.input.genre.find_all_genres_usecase.FindAllGenresResponse;
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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final SaveMovieUsecase saveMovieUsecase;
    private final FindMovieByIdUsecase findMovieByIdUsecase;
    private final FindAllMoviesUsecase findAllMoviesUsecase;
    private final UpdateMovieUsecase updateMovieUsecase;
    private final DeleteMovieUsecase deleteMovieUsecase;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity createMovie(@Valid @RequestBody SaveMovieRequest saveMovieRequest){
        SaveMovieResponse saveMovieResponse = saveMovieUsecase.save(saveMovieRequest);
        return new ResponseEntity<>(saveMovieResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity getMovieById(@PathVariable Long id) {
        FindMovieByIdResponse movie = findMovieByIdUsecase.findById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CONSUMER')")
    public ResponseEntity getAllMovies() {
        List<FindAllMoviesResponse> movies = findAllMoviesUsecase.findAll();
        return new ResponseEntity(movies, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity deleteMovieById(@PathVariable Long id) {
        deleteMovieUsecase.deleteById(id);
        return new ResponseEntity("Movie successfully deleted!", HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity updateMovieById(@PathVariable Long id,
                                          @Valid @RequestBody UpdateMovieRequest updateMovieRequest){
        UpdateMovieResponse movie = updateMovieUsecase.update(id, updateMovieRequest);
        return new ResponseEntity(movie, HttpStatus.OK);
    }
}
