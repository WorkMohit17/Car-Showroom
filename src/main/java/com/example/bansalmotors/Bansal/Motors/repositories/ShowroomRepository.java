package com.example.bansalmotors.Bansal.Motors.repositories;

import com.example.bansalmotors.Bansal.Motors.entities.ShowroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowroomRepository extends JpaRepository<ShowroomEntity, Long> {

    List<ShowroomEntity> findByLocationContainingIgnoreCase(String keyword);
    List<ShowroomEntity> findByManagerNameIgnoreCase(String managerName);
    List<ShowroomEntity> findByManagerNameContainingIgnoreCase(String keyword);
}
