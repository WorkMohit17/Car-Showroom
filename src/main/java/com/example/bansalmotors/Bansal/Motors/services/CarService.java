package com.example.bansalmotors.Bansal.Motors.services;

import com.example.bansalmotors.Bansal.Motors.dtos.CarDTO;
import com.example.bansalmotors.Bansal.Motors.entities.CarEntity;
import com.example.bansalmotors.Bansal.Motors.entities.ShowroomEntity;
import com.example.bansalmotors.Bansal.Motors.mappers.CarMapper;
import com.example.bansalmotors.Bansal.Motors.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    /*
     ✅ BASIC CRUD OPERATIONS
     */

    public Optional<CarDTO> createCar(CarDTO dto) {
        CarEntity entity = CarMapper.toEntity(dto);
        CarEntity saved = repository.save(entity);
        return Optional.of(CarMapper.toDTO(saved));
    }

    public Optional<CarDTO> getCarById(Long id) {
        return repository.findById(id).map(CarMapper::toDTO);
    }

    public Optional<List<CarDTO>> getAllCars() {
        List<CarDTO> list = repository.findAll()
                .stream()
                .map(CarMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

    public Optional<CarDTO> updateCar(Long id, CarDTO dto) {
        CarEntity car = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with ID: " + id));

        if (dto.getModel() != null)
            car.setModel(dto.getModel());

        if (dto.getBrand() != null)
            car.setBrand(dto.getBrand());

        if (dto.getPrice() != null)
            car.setPrice(dto.getPrice());

        if (dto.getFuelType() != null)
            car.setFuelType(dto.getFuelType());

        if (dto.getManufactureYear() != null)
            car.setManufactureYear(dto.getManufactureYear());

        if (dto.getAvailable() != null)
            car.setAvailable(dto.getAvailable());

        if (dto.getShowroomId() != null) {
            ShowroomEntity showroom = new ShowroomEntity();
            showroom.setId(dto.getShowroomId());
            car.setShowroom(showroom);
        }

        CarEntity updated = repository.save(car);
        return Optional.of(CarMapper.toDTO(updated));
    }

    public Optional<Boolean> deleteCar(Long id) {
        if (!repository.existsById(id)) {
            return Optional.empty();
        }
        repository.deleteById(id);
        return Optional.of(true);
    }

    /*
     🔍 OPTIONAL FILTER METHODS
     */

    public Optional<List<CarDTO>> getCarsByBrand(String brand) {
        List<CarDTO> list = repository.findByBrandIgnoreCase(brand)
                .stream()
                .map(CarMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

    public Optional<List<CarDTO>> getCarsByModel(String model) {
        List<CarDTO> list = repository.findByModelIgnoreCase(model)
                .stream()
                .map(CarMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

    public Optional<List<CarDTO>> getCarsByPriceRange(Double minPrice, Double maxPrice) {
        List<CarDTO> list = repository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(CarMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

    public Optional<List<CarDTO>> getCarsByFuelType(String fuelType) {
        List<CarDTO> list = repository.findByFuelTypeIgnoreCase(fuelType)
                .stream()
                .map(CarMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

    public Optional<List<CarDTO>> getCarsByAvailability(boolean available) {
        List<CarDTO> list = repository.findByAvailable(available)
                .stream()
                .map(CarMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

    public Optional<List<CarDTO>> getCarsByShowroomId(Long showroomId) {
        List<CarDTO> list = repository.findByShowroomId(showroomId)
                .stream()
                .map(CarMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

    /*
        OPTIONAL STATUS OPERATIONS
     */

    public Optional<CarDTO> markCarAsSold(Long carId) {
        CarEntity car = repository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with ID: " + carId));

        if (!car.getAvailable()) {
            throw new RuntimeException("Car is already marked as sold.");
        }

        car.setAvailable(false);
        return Optional.of(CarMapper.toDTO(repository.save(car)));
    }

    public Optional<CarDTO> markCarAsAvailable(Long carId) {
        CarEntity car = repository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with ID: " + carId));

        if (car.getAvailable()) {
            throw new RuntimeException("Car is already available.");
        }

        car.setAvailable(true);
        return Optional.of(CarMapper.toDTO(repository.save(car)));
    }
}
