package com.example.bansalmotors.Bansal.Motors.services;

import com.example.bansalmotors.Bansal.Motors.dtos.CustomerDTO;
import com.example.bansalmotors.Bansal.Motors.entities.CustomerEntity;
import com.example.bansalmotors.Bansal.Motors.mappers.CustomerMapper;
import com.example.bansalmotors.Bansal.Motors.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    /*
      BASIC CRUD OPERATIONS
     */

    public Optional<CustomerDTO> createCustomer(CustomerDTO dto) {
        CustomerEntity entity = CustomerMapper.toEntity(dto);
        CustomerEntity saved = repository.save(entity);
        return Optional.of(CustomerMapper.toDTO(saved));
    }

    public Optional<CustomerDTO> getCustomerById(Long id) {
        return repository.findById(id).map(CustomerMapper::toDTO);
    }

    public Optional<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> list = repository.findAll()
                .stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }

    public Optional<CustomerDTO> updateCustomer(Long id, CustomerDTO dto) {
        CustomerEntity customer = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));

        if (dto.getName() != null)
            customer.setName(dto.getName());

        if (dto.getEmail() != null)
            customer.setEmail(dto.getEmail());

        if (dto.getPhoneNumber() != null)
            customer.setPhoneNumber(dto.getPhoneNumber());

        if (dto.getAddress() != null)
            customer.setAddress(dto.getAddress());

        CustomerEntity updated = repository.save(customer);
        return Optional.of(CustomerMapper.toDTO(updated));
    }

    public Optional<Boolean> deleteCustomer(Long id) {
        if (!repository.existsById(id)) {
            return Optional.empty();
        }
        repository.deleteById(id);
        return Optional.of(true);
    }

    /*
        OPTIONAL FILTER METHODS
     */

    public Optional<List<CustomerDTO>> searchCustomersByName(String keyword) {
        List<CustomerDTO> list = repository.findAll().stream()
                .filter(c -> c.getName().toLowerCase().contains(keyword.toLowerCase()))
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
        return Optional.of(list);
    }
}
