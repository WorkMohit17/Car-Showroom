package com.example.bansalmotors.Bansal.Motors.repositories;

import com.example.bansalmotors.Bansal.Motors.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
