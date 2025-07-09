package com.example.bansalmotors.Bansal.Motors.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String model;
    private String brand;
    private Double price;
    private Boolean available;
    private int manufactureYear;
    private String fuelType;
    private Long showroomId;
    private List<Long> bookingIds;
}
