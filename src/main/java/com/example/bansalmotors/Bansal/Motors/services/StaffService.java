package com.example.bansalmotors.Bansal.Motors.services;

import com.example.bansalmotors.Bansal.Motors.dtos.StaffDTO;
import com.example.bansalmotors.Bansal.Motors.entities.ShowroomEntity;
import com.example.bansalmotors.Bansal.Motors.entities.StaffEntity;
import com.example.bansalmotors.Bansal.Motors.mappers.StaffMapper;
import com.example.bansalmotors.Bansal.Motors.repositories.StaffRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffService {

    private final StaffRepository repository;

    public StaffService(StaffRepository repository) {
        this.repository = repository;
    }

    /*
      BASIC CRUD OPERATIONS
     */

    public Optional<StaffDTO> createStaff(StaffDTO dto) {
        StaffEntity entity = StaffMapper.toEntity(dto);
        StaffEntity saved = repository.save(entity);
        return Optional.of(StaffMapper.toDTO(saved));
    }

    public Optional<StaffDTO> getStaffById(Long id) {
        return repository.findById(id)
                .map(StaffMapper::toDTO);
    }

    public Optional<List<StaffDTO>> getAllStaff() {
        List<StaffDTO> list = repository.findAll().stream()
                .map(StaffMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

    public Optional<StaffDTO> updateStaff(Long id, StaffDTO dto) {
        StaffEntity staff = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Staff not found with ID: " + id));

        if (dto.getName() != null)
            staff.setName(dto.getName());

        if (dto.getRole() != null)
            staff.setRole(dto.getRole());

        if (dto.getSalary() != null)
            staff.setSalary(dto.getSalary());

        if (dto.getShowroomId() != null) {
            ShowroomEntity showroom = new ShowroomEntity();
            showroom.setId(dto.getShowroomId());
            staff.setShowroom(showroom);
        }

        StaffEntity updated = repository.save(staff);
        return Optional.of(StaffMapper.toDTO(updated));
    }

    public Optional<Boolean> deleteStaff(Long id) {
        if (!repository.existsById(id)) {
            return Optional.empty();
        }
        repository.deleteById(id);
        return Optional.of(true);
    }

    /*
      OPTIONAL FILTER METHODS
     */

    public Optional<List<StaffDTO>> getStaffByRole(String role) {
        List<StaffDTO> list = repository.findByRoleIgnoreCase(role)
                .stream()
                .map(StaffMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

    public Optional<List<StaffDTO>> getStaffBySalaryAbove(double minSalary) {
        List<StaffDTO> list = repository.findBySalaryGreaterThanEqual(minSalary)
                .stream()
                .map(StaffMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

    public Optional<List<StaffDTO>> getStaffByShowroomId(Long showroomId) {
        List<StaffDTO> list = repository.findByShowroom_Id(showroomId)
                .stream()
                .map(StaffMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

}
