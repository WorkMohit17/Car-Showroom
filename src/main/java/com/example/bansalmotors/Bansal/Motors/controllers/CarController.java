package com.example.bansalmotors.Bansal.Motors.controllers;

import com.example.bansalmotors.Bansal.Motors.dtos.CarDTO;
import com.example.bansalmotors.Bansal.Motors.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    /*
      BASIC CRUD
     */

    @PostMapping
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO dto) {
        return service.createCar(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        return service.getCarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return service.getAllCars()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @RequestBody CarDTO dto) {
        return service.updateCar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable Long id) {
        return service.deleteCar(id)
                .map(result -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    /*
      FILTERS
     */

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<CarDTO>> getCarsByBrand(@PathVariable String brand) {
        return service.getCarsByBrand(brand)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<List<CarDTO>> getCarsByModel(@PathVariable String model) {
        return service.getCarsByModel(model)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<CarDTO>> getCarsByPriceRange(@RequestParam Double min,
                                                            @RequestParam Double max) {
        return service.getCarsByPriceRange(min, max)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/fuel-type/{fuelType}")
    public ResponseEntity<List<CarDTO>> getCarsByFuelType(@PathVariable String fuelType) {
        return service.getCarsByFuelType(fuelType)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/availability")
    public ResponseEntity<List<CarDTO>> getCarsByAvailability(@RequestParam boolean available) {
        return service.getCarsByAvailability(available)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/showroom/{showroomId}")
    public ResponseEntity<List<CarDTO>> getCarsByShowroomId(@PathVariable Long showroomId) {
        return service.getCarsByShowroomId(showroomId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    /*
      STATUS CONTROL
     */

    @PutMapping("/{id}/mark-sold")
    public ResponseEntity<CarDTO> markCarAsSold(@PathVariable Long id) {
        return service.markCarAsSold(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}/mark-available")
    public ResponseEntity<CarDTO> markCarAsAvailable(@PathVariable Long id) {
        return service.markCarAsAvailable(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
