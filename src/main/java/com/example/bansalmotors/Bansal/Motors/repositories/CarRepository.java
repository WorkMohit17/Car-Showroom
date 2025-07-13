package com.example.bansalmotors.Bansal.Motors.repositories;

import com.example.bansalmotors.Bansal.Motors.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<CarEntity, Long> {

    List<CarEntity> findByBrandIgnoreCase(String brand);
    List<CarEntity> findByModelIgnoreCase(String model);
    List<CarEntity> findByPriceBetween(Double minPrice, Double maxPrice);
    List<CarEntity> findByFuelTypeIgnoreCase(String fuelType);
    List<CarEntity> findByAvailable(boolean available);
    List<CarEntity> findByShowroomId(Long showroomId);
}
