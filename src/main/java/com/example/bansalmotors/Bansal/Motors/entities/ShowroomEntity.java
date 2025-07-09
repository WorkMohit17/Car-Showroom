package com.example.bansalmotors.Bansal.Motors.entities;

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
@Table(name = "showroom_table")
public class ShowroomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String managerName;

    @OneToMany(mappedBy = "showroom", cascade = CascadeType.ALL)
    private List<CarEntity> cars;

    @OneToMany(mappedBy = "showroom", cascade = CascadeType.ALL)
    private List<StaffEntity> staffMembers;

}
