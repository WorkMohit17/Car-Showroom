package com.example.bansalmotors.Bansal.Motors.repositories;

import com.example.bansalmotors.Bansal.Motors.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}
