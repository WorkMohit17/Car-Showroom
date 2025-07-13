package com.example.bansalmotors.Bansal.Motors.services;

import com.example.bansalmotors.Bansal.Motors.dtos.ShowroomDTO;
import com.example.bansalmotors.Bansal.Motors.entities.ShowroomEntity;
import com.example.bansalmotors.Bansal.Motors.mappers.ShowroomMapper;
import com.example.bansalmotors.Bansal.Motors.repositories.ShowroomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowroomService {

    private final ShowroomRepository repository;

    public ShowroomService(ShowroomRepository repository) {
        this.repository = repository;
    }

    /*
       BASIC CRUD OPERATIONS
     */

    public Optional<ShowroomDTO> createShowroom(ShowroomDTO dto) {
        ShowroomEntity entity = ShowroomMapper.toEntity(dto);
        ShowroomEntity saved = repository.save(entity);
        return Optional.of(ShowroomMapper.toDTO(saved));
    }

    public Optional<ShowroomDTO> getShowroomById(Long id) {
        return repository.findById(id)
                .map(ShowroomMapper::toDTO);
    }

    public Optional<List<ShowroomDTO>> getAllShowrooms() {
        List<ShowroomDTO> list = repository.findAll()
                .stream()
                .map(ShowroomMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

    public Optional<ShowroomDTO> updateShowroom(Long id, ShowroomDTO dto) {
        ShowroomEntity showroom = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Showroom not found with ID: " + id));

        if (dto.getLocation() != null)
            showroom.setLocation(dto.getLocation());

        if (dto.getManagerName() != null)
            showroom.setManagerName(dto.getManagerName());

        ShowroomEntity updated = repository.save(showroom);
        return Optional.of(ShowroomMapper.toDTO(updated));
    }

    public Optional<Boolean> deleteShowroom(Long id) {
        if (!repository.existsById(id)) {
            return Optional.empty();
        }
        repository.deleteById(id);
        return Optional.of(true);
    }

    /*
      SEARCH & FILTER USING REPOSITORY
     */

    public Optional<List<ShowroomDTO>> searchShowroomsByLocation(String keyword) {
        List<ShowroomDTO> list = repository.findByLocationContainingIgnoreCase(keyword)
                .stream()
                .map(ShowroomMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

    public Optional<List<ShowroomDTO>> getShowroomsByManagerName(String managerName) {
        List<ShowroomDTO> list = repository.findByManagerNameIgnoreCase(managerName)
                .stream()
                .map(ShowroomMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

    public Optional<List<ShowroomDTO>> searchShowroomsByManagerKeyword(String keyword) {
        List<ShowroomDTO> list = repository.findByManagerNameContainingIgnoreCase(keyword)
                .stream()
                .map(ShowroomMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

    /*
       COLLECTION-BASED FILTERS
     */

    public Optional<List<ShowroomDTO>> getShowroomsWithCars() {
        List<ShowroomDTO> filtered = repository.findAll().stream()
                .filter(s -> s.getCars() != null && !s.getCars().isEmpty())
                .map(ShowroomMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(filtered);
    }

    public Optional<List<ShowroomDTO>> getShowroomsWithStaff() {
        List<ShowroomDTO> filtered = repository.findAll().stream()
                .filter(s -> s.getStaffMembers() != null && !s.getStaffMembers().isEmpty())
                .map(ShowroomMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(filtered);
    }

    public Optional<List<ShowroomDTO>> getActiveShowrooms() {
        List<ShowroomDTO> filtered = repository.findAll().stream()
                .filter(s -> s.getCars() != null && !s.getCars().isEmpty()
                        && s.getStaffMembers() != null && !s.getStaffMembers().isEmpty())
                .map(ShowroomMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(filtered);
    }
}
