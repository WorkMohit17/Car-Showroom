package com.example.bansalmotors.Bansal.Motors.mappers;

import com.example.bansalmotors.Bansal.Motors.dtos.CustomerDTO;
import com.example.bansalmotors.Bansal.Motors.entities.CustomerEntity;

public class CustomerMapper {

    public static CustomerEntity toEntity(CustomerDTO dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        return entity;
    }

    public static CustomerDTO toDTO(CustomerEntity entity) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        return dto;
    }
}
