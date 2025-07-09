package com.example.bansalmotors.Bansal.Motors.mappers;

import com.example.bansalmotors.Bansal.Motors.dtos.BookingDTO;
import com.example.bansalmotors.Bansal.Motors.entities.BookingEntity;
import com.example.bansalmotors.Bansal.Motors.entities.CarEntity;
import com.example.bansalmotors.Bansal.Motors.entities.CustomerEntity;

public class BookingMapper {

    public static BookingDTO toDTO(BookingEntity entity){
        return BookingDTO.builder()
                .id(entity.getId())
                .bookingDate(entity.getBookingDate())
                .status(entity.getStatus())
                .carId(entity.getCar().getId())
                .customerId(entity.getCustomer().getId())
                .build();
    }

    public static BookingEntity toEntity(BookingDTO dto){
        BookingEntity entity = new BookingEntity();
        entity.setId(dto.getId());
        entity.setBookingDate(dto.getBookingDate());
        entity.setStatus(dto.getStatus());

        CarEntity car = new CarEntity();
        car.setId(dto.getCustomerId());
        entity.setCar(car);

        CustomerEntity customer = new CustomerEntity();
        customer.setId(dto.getCustomerId());
        entity.setCustomer(customer);

        return entity;
    }

}
