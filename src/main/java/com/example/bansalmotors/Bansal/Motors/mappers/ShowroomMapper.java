package com.example.bansalmotors.Bansal.Motors.mappers;

import com.example.bansalmotors.Bansal.Motors.dtos.ShowroomDTO;
import com.example.bansalmotors.Bansal.Motors.entities.CarEntity;
import com.example.bansalmotors.Bansal.Motors.entities.ShowroomEntity;
import com.example.bansalmotors.Bansal.Motors.entities.StaffEntity;

import java.util.ArrayList;
import java.util.List;

public class ShowroomMapper {

    public static ShowroomEntity toEntity(ShowroomDTO dto) {
        ShowroomEntity entity = new ShowroomEntity();
        entity.setId(dto.getId());
        entity.setLocation(dto.getLocation());
        entity.setManagerName(dto.getManagerName());

        if (dto.getCarIds() != null && !dto.getCarIds().isEmpty()) {
            List<CarEntity> cars = new ArrayList<>();
            for (Long carId : dto.getCarIds()) {
                CarEntity car = new CarEntity();
                car.setId(carId);
                car.setShowroom(entity);
                cars.add(car);
            }
            entity.setCars(cars);
        }

        if (dto.getStaffMemberIds() != null && !dto.getStaffMemberIds().isEmpty()) {
            List<StaffEntity> staffList = new ArrayList<>();
            for (Long staffId : dto.getStaffMemberIds()) {
                StaffEntity staff = new StaffEntity();
                staff.setId(staffId);
                staff.setShowroom(entity);
                staffList.add(staff);
            }
            entity.setStaffMembers(staffList);
        }

        return entity;
    }

    public static ShowroomDTO toDTO(ShowroomEntity entity) {
        ShowroomDTO dto = new ShowroomDTO();
        dto.setId(entity.getId());
        dto.setLocation(entity.getLocation());
        dto.setManagerName(entity.getManagerName());

        List<Long> carIds = new ArrayList<>();
        if (entity.getCars() != null) {
            for (CarEntity car : entity.getCars()) {
                carIds.add(car.getId());
            }
        }
        dto.setCarIds(carIds);

        List<Long> staffIds = new ArrayList<>();
        if (entity.getStaffMembers() != null) {
            for (StaffEntity staff : entity.getStaffMembers()) {
                staffIds.add(staff.getId());
            }
        }
        dto.setStaffMemberIds(staffIds);

        return dto;
    }
}
