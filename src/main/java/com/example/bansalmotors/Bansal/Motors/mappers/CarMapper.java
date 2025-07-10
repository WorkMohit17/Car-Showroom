package com.example.bansalmotors.Bansal.Motors.mappers;

import com.example.bansalmotors.Bansal.Motors.dtos.CarDTO;
import com.example.bansalmotors.Bansal.Motors.entities.BookingEntity;
import com.example.bansalmotors.Bansal.Motors.entities.CarEntity;
import com.example.bansalmotors.Bansal.Motors.entities.ShowroomEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {

    public static CarDTO toDTO(CarEntity entity) {
        List<Long> bookingIds = entity.getBookings().stream()
                .map(BookingEntity::getId)
                .collect(Collectors.toList());

        return CarDTO.builder()
                .id(entity.getId())
                .model(entity.getModel())
                .brand(entity.getBrand())
                .price(entity.getPrice())
                .available(entity.getAvailable())
                .manufactureYear(entity.getManufactureYear())
                .fuelType(entity.getFuelType())
                .showroomId(entity.getShowroom().getId())
                .bookingIds(bookingIds)
                .build();
    }

    public static CarEntity toEntity(CarDTO dto) {
        CarEntity entity = new CarEntity();
        entity.setId(dto.getId());
        entity.setModel(dto.getModel());
        entity.setBrand(dto.getBrand());
        entity.setPrice(dto.getPrice());
        entity.setAvailable(dto.getAvailable());
        entity.setManufactureYear(dto.getManufactureYear());
        entity.setFuelType(dto.getFuelType());

        ShowroomEntity showroom = new ShowroomEntity();
        showroom.setId(dto.getShowroomId());
        entity.setShowroom(showroom);

        List<BookingEntity> bookings = new ArrayList<>();
        if (dto.getBookingIds() != null && !dto.getBookingIds().isEmpty()) {
            for (Long id : dto.getBookingIds()) {
                BookingEntity booking = new BookingEntity();
                booking.setId(id);
                bookings.add(booking);
            }
        }
        entity.setBookings(bookings);

        return entity;
    }


}
