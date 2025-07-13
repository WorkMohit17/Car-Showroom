package com.example.bansalmotors.Bansal.Motors.controllers;

import com.example.bansalmotors.Bansal.Motors.dtos.StaffDTO;
import com.example.bansalmotors.Bansal.Motors.services.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService service;

    public StaffController(StaffService service) {
        this.service = service;
    }

    /*
      BASIC CRUD
     */

    @PostMapping
    public ResponseEntity<StaffDTO> createStaff(@RequestBody StaffDTO dto) {
        return service.createStaff(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> getStaffById(@PathVariable Long id) {
        return service.getStaffById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<StaffDTO>> getAllStaff() {
        return service.getAllStaff()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDTO> updateStaff(@PathVariable Long id, @RequestBody StaffDTO dto) {
        return service.updateStaff(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStaff(@PathVariable Long id) {
        return service.deleteStaff(id)
                .map(result -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    /*
      FILTERING
     */

    @GetMapping("/role")
    public ResponseEntity<List<StaffDTO>> getStaffByRole(@RequestParam String role) {
        return service.getStaffByRole(role)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/salary-above")
    public ResponseEntity<List<StaffDTO>> getStaffBySalaryAbove(@RequestParam double minSalary) {
        return service.getStaffBySalaryAbove(minSalary)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/showroom/{showroomId}")
    public ResponseEntity<List<StaffDTO>> getStaffByShowroomId(@PathVariable Long showroomId) {
        return service.getStaffByShowroomId(showroomId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
