package com.example.web.controller.genre;

import com.example.ports.input.genre.delete_genre_usecase.DeleteGenreUsecase;
import com.example.ports.input.genre.find_all_genres_usecase.FindAllGenresResponse;
import com.example.ports.input.genre.find_all_genres_usecase.FindAllGenresUsecase;
import com.example.ports.input.genre.find_by_id_usecase.FindGenreByIdResponse;
import com.example.ports.input.genre.find_by_id_usecase.FindGenreByIdUsecase;
import com.example.ports.input.genre.find_by_type_usecase.FindGenreByTypeResponse;
import com.example.ports.input.genre.find_by_type_usecase.FindGenreByTypeUsecase;
import com.example.ports.input.genre.save_genre_usecase.SaveGenreRequest;
import com.example.ports.input.genre.save_genre_usecase.SaveGenreResponse;
import com.example.ports.input.genre.save_genre_usecase.SaveGenreUsecase;
import com.example.ports.input.genre.update_genre_usecase.UpdateGenreRequest;
import com.example.ports.input.genre.update_genre_usecase.UpdateGenreResponse;
import com.example.ports.input.genre.update_genre_usecase.UpdateGenreUsecase;
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
@RequestMapping("/api/genres")
public class GenreController {

    private final SaveGenreUsecase saveGenreUsecase;
    private final FindAllGenresUsecase findAllGenresUsecase;
    private final FindGenreByIdUsecase findGenreByIdUsecase;
    private final DeleteGenreUsecase deleteGenreUsecase;
    private final UpdateGenreUsecase updateGenreUsecase;
    private final FindGenreByTypeUsecase findGenreByTypeUsecase;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity createGenre(@Valid @RequestBody SaveGenreRequest saveGenreRequest) {
        SaveGenreResponse saveGenreResponse = saveGenreUsecase.save(saveGenreRequest);
        return new ResponseEntity<>(saveGenreResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity getGenreById(@PathVariable Long id) {
        FindGenreByIdResponse genre = findGenreByIdUsecase.findById(id);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllGenres() {
        List<FindAllGenresResponse> genres = findAllGenresUsecase.findAll();
        return new ResponseEntity(genres, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity deleteGenreById(@PathVariable Long id) {
        deleteGenreUsecase.deleteById(id);
        return new ResponseEntity("Genre successfully deleted!", HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity updateGenreById(@PathVariable Long id,
                                          @Valid @RequestBody UpdateGenreRequest updateGenreRequest){
        UpdateGenreResponse genre = updateGenreUsecase.update(id, updateGenreRequest);
        return new ResponseEntity(genre, HttpStatus.OK);
    }

}
