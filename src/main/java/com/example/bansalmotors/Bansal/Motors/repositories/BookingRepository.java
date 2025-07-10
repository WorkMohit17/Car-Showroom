package com.example.bansalmotors.Bansal.Motors.repositories;

import com.example.bansalmotors.Bansal.Motors.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findAllByStatus(String status);

    List<BookingEntity> findAllByCustomerId(Long customerId);

    List<BookingEntity> findAllByCarId(Long carId);

    List<BookingEntity> findAllByDate(LocalDate date);

    List<BookingEntity> findAllByBookingDateBetween(LocalDate startDate, LocalDate endDate);
}
