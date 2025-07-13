package com.example.bansalmotors.Bansal.Motors.repositories;

import com.example.bansalmotors.Bansal.Motors.entities.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {

    List<StaffEntity> findByRoleIgnoreCase(String role);

    List<StaffEntity> findBySalaryGreaterThanEqual(Double salary);

    List<StaffEntity> findByShowroom_Id(Long showroomId);
}
