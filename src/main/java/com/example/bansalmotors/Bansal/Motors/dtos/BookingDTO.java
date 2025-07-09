package com.example.bansalmotors.Bansal.Motors.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long id;
    private LocalDate bookingDate;
    private String status;
    private Long carId;
    private Long customerId;
}
