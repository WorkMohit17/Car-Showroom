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
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private List<Long> bookingIds;
}
