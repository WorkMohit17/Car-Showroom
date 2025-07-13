package com.example.bansalmotors.Bansal.Motors.services;

import com.example.bansalmotors.Bansal.Motors.dtos.BookingDTO;
import com.example.bansalmotors.Bansal.Motors.entities.BookingEntity;
import com.example.bansalmotors.Bansal.Motors.entities.CarEntity;
import com.example.bansalmotors.Bansal.Motors.entities.CustomerEntity;
import com.example.bansalmotors.Bansal.Motors.mappers.BookingMapper;
import com.example.bansalmotors.Bansal.Motors.repositories.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    /*
        CRUD FUNCTIONS
     */

    public Optional<BookingDTO> createBooking(BookingDTO bookingDTO){
        String newStatus = bookingDTO.getStatus().toUpperCase();
        bookingDTO.setStatus(newStatus);
        BookingEntity entity = BookingMapper.toEntity(bookingDTO);
        BookingEntity saved = repository.save(entity);
        return Optional.of(BookingMapper.toDTO(saved));
    }

    public Optional<BookingDTO> getBookingById(Long id) {
        BookingEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking with id: " + id + " does not exist"));
        return Optional.of(BookingMapper.toDTO(entity));
    }

    public Optional<List<BookingDTO>> getAllBookings(){
        List<BookingEntity> entities = repository.findAll();
        return Optional.of(entities.stream().map(BookingMapper::toDTO).collect(Collectors.toList()));
   }

    public Optional<BookingDTO> updateBooking(Long id, BookingDTO bookingDTO) {
        BookingEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking with id: " + id + " does not exist"));

        if(bookingDTO.getBookingDate() != null)
            entity.setBookingDate(bookingDTO.getBookingDate());

        if(bookingDTO.getStatus() != null)
            entity.setStatus(bookingDTO.getStatus());

        if (bookingDTO.getCarId() != null) {
            CarEntity car = new CarEntity();
            car.setId(bookingDTO.getCarId());
            entity.setCar(car);
        }

        if (bookingDTO.getCustomerId() != null) {
            CustomerEntity customer = new CustomerEntity();
            customer.setId(bookingDTO.getCustomerId());
            entity.setCustomer(customer);
        }

        BookingEntity updatedEntity = repository.save(entity);

        BookingDTO updatedDTO = BookingMapper.toDTO(updatedEntity);
        return Optional.of(updatedDTO);
    }

    public boolean deleteBooking(Long id){
        isExistByid(id);
        repository.deleteById(id);
        return true;
    }

    private void isExistByid(Long id) {
        BookingEntity entity = repository.findById(id).orElse(null);
        if(entity == null)
            throw new EntityNotFoundException("Booking with id: "+id+" not found");
    }


    /*
        QUERY/FILTER FUNCTIONS
     */

    public List<BookingDTO> getBookingsByCustomerId(Long customerId){
        List<BookingEntity> entities = repository.findAllByCustomerId(customerId);
        return entities.stream()
                .map(BookingMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<BookingDTO> getBookingsByCarId(Long carId){
        List<BookingEntity> entities = repository.findAllByCarId(carId);
        return entities.stream()
                .map(BookingMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<BookingDTO> getBookingsByStatus(String status){
        List<BookingEntity> entities = repository.findAllByStatus(status);
        return entities.stream()
                .map(BookingMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<BookingDTO> getBookingsByDate(LocalDate date){
        List<BookingEntity> entities = repository.findAllByDate(date);
        return entities.stream()
                .map(BookingMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<BookingDTO> getBookingsBetweenDates(LocalDate startDate, LocalDate endDate){
        List<BookingEntity> entities = repository.findAllByBookingDateBetween(startDate, endDate);
        return entities.stream()
                .map(BookingMapper::toDTO)
                .collect(Collectors.toList());
    }


    /*
        STATUS FUNCTIONS
        PENDING or CONFIRMED or CANCELLED
     */

    public BookingDTO confirmBooking(Long bookingId) {
        BookingEntity booking = repository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + bookingId));

        if ("CONFIRMED".equalsIgnoreCase(booking.getStatus())) {
            throw new RuntimeException("Booking is already confirmed.");
        }

        booking.setStatus("CONFIRMED");
        BookingEntity updatedBooking = repository.save(booking);
        return BookingMapper.toDTO(updatedBooking);
    }

    public BookingDTO cancelBooking(Long bookingId) {
        BookingEntity booking = repository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + bookingId));

        if ("CANCELLED".equalsIgnoreCase(booking.getStatus())) {
            throw new RuntimeException("Booking is already cancelled.");
        }

        booking.setStatus("CANCELLED");
        BookingEntity updatedBooking = repository.save(booking);
        return BookingMapper.toDTO(updatedBooking);
    }
}
