package com.example.bansalmotors.Bansal.Motors.mappers;

import com.example.bansalmotors.Bansal.Motors.dtos.StaffDTO;
import com.example.bansalmotors.Bansal.Motors.entities.ShowroomEntity;
import com.example.bansalmotors.Bansal.Motors.entities.StaffEntity;

public class StaffMapper {

    public static StaffEntity toEntity(StaffDTO dto) {
        StaffEntity entity = new StaffEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setRole(dto.getRole());
        entity.setSalary(dto.getSalary());

        if (dto.getShowroomId() != null) {
            ShowroomEntity showroom = new ShowroomEntity();
            showroom.setId(dto.getShowroomId());
            entity.setShowroom(showroom);
        }

        return entity;
    }

    public static StaffDTO toDTO(StaffEntity entity) {
        StaffDTO dto = new StaffDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setRole(entity.getRole());
        dto.setSalary(entity.getSalary());

        if (entity.getShowroom() != null)
            dto.setShowroomId(entity.getShowroom().getId());

        return dto;
    }
}
