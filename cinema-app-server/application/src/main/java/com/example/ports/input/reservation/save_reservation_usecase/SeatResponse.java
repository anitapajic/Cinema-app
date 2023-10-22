package com.example.ports.input.reservation.save_reservation_usecase;

import com.example.model.enums.SeatStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SeatResponse {
    private Long id;
    @NotNull
    private SeatStatus seatStatus;
    @NotNull
    private Integer row;
    @NotNull
    private Integer col;
    private Long screeningId;

    @Override
    public String toString() {
        return "SeatResponse{" +
                "id=" + id +
                ", seatStatus=" + seatStatus +
                ", row=" + row +
                ", col=" + col +
                ", screeningId=" + screeningId +
                '}';
    }
}
