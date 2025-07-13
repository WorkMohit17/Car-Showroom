package com.example.bansalmotors.Bansal.Motors.entities;

import com.example.bansalmotors.Bansal.Motors.audit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car_table")
public class CarEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Boolean available;

    @Column(nullable = false)
    private int manufactureYear;

    @Column(nullable = false)
    private String fuelType;

    @ManyToOne
    @JoinColumn(name = "showroom_id")
    private ShowroomEntity showroom;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<BookingEntity> bookings;

}
