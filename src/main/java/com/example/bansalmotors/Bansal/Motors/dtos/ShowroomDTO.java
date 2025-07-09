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
public class ShowroomDTO {
    private Long id;
    private String location;
    private String managerName;
    private List<Long> carIds;
    private List<Long> staffMemberIds;
}
