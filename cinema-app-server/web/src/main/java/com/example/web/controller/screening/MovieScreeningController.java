package com.example.web.controller.screening;

import com.example.ports.input.screening.delete_screening_usecase.DeleteScreeningUsecase;
import com.example.ports.input.screening.find_all_by_movie_id_usecase.FindAllScreeningsByMovieIdResponse;
import com.example.ports.input.screening.find_all_by_movie_id_usecase.FindAllScreeningsByMovieIdUsecase;
import com.example.ports.input.screening.find_all_screenings_by_date_usecase.FindAllScreeningsByDateResponse;
import com.example.ports.input.screening.find_all_screenings_by_date_usecase.FindAllScreeningsByDateUsecase;
import com.example.ports.input.screening.find_all_screenings_usecase.FindAllScreeningsResponse;
import com.example.ports.input.screening.find_all_screenings_usecase.FindAllScreeningsUsecase;
import com.example.ports.input.screening.find_by_id_usecase.FindScreeningByIdResponse;
import com.example.ports.input.screening.find_by_id_usecase.FindScreeningByIdUsecase;
import com.example.ports.input.screening.save_screening_usecase.SaveScreeningRequest;
import com.example.ports.input.screening.save_screening_usecase.SaveScreeningResponse;
import com.example.ports.input.screening.save_screening_usecase.SaveScreeningUsecase;
import com.example.ports.input.screening.update_screening_usecase.UpdateScreeningRequest;
import com.example.ports.input.screening.update_screening_usecase.UpdateScreeningResponse;
import com.example.ports.input.screening.update_screening_usecase.UpdateScreeningUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/screenings")
public class MovieScreeningController {
    private final SaveScreeningUsecase saveScreeningUsecase;
    private final DeleteScreeningUsecase deleteScreeningUsecase;
    private final UpdateScreeningUsecase updateScreeningUsecase;
    private final FindAllScreeningsUsecase findAllScreeningsUsecase;
    private final FindScreeningByIdUsecase findScreeningByIdUsecase;
    private final FindAllScreeningsByMovieIdUsecase findAllScreeningsByMovieIdUsecase;
    private final FindAllScreeningsByDateUsecase findAllScreeningsByDateUsecase;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity createMovieScreening(@Valid @RequestBody SaveScreeningRequest saveScreeningRequest){
        SaveScreeningResponse saveScreeningResponse = saveScreeningUsecase.save(saveScreeningRequest);
        return new ResponseEntity<>(saveScreeningResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CONSUMER')")
    public ResponseEntity getScreeningById(@PathVariable Long id) {
        FindScreeningByIdResponse movieScreening = findScreeningByIdUsecase.findById(id);
        return new ResponseEntity<>(movieScreening, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity getAllScreenings() {
        List<FindAllScreeningsResponse> movieScreenings = findAllScreeningsUsecase.findAll();
        return new ResponseEntity(movieScreenings, HttpStatus.OK);
    }
    @GetMapping(value = "/movie/{movieId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CONSUMER')")
    public ResponseEntity getAllScreeningsByMovieId(@PathVariable Long movieId) {
        List<FindAllScreeningsByMovieIdResponse> movieScreenings = findAllScreeningsByMovieIdUsecase.findByMovieId(movieId);
        return new ResponseEntity(movieScreenings, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity deleteScreeningById(@PathVariable Long id) {
        deleteScreeningUsecase.deleteById(id);
        return new ResponseEntity("Movie screening successfully deleted!", HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity updateScreeningById(@PathVariable Long id,
                                              @Valid @RequestBody UpdateScreeningRequest updateScreeningRequest){
        UpdateScreeningResponse movieScreening = updateScreeningUsecase.update(id, updateScreeningRequest);
        return new ResponseEntity(movieScreening, HttpStatus.OK);
    }

    @GetMapping(value = "/date")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CONSUMER')")
    public ResponseEntity getAllScreeningsByDate( @RequestParam
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  LocalDate date) {
        List<FindAllScreeningsByDateResponse> movieScreenings = findAllScreeningsByDateUsecase.findAllByDate(date);
        return new ResponseEntity(movieScreenings, HttpStatus.OK);
    }


}
