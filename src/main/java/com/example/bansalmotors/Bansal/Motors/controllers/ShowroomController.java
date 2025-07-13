package com.example.bansalmotors.Bansal.Motors.controllers;

package com.example.bansalmotors.Bansal.Motors.controllers;

import com.example.bansalmotors.Bansal.Motors.dtos.ShowroomDTO;
import com.example.bansalmotors.Bansal.Motors.services.ShowroomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showrooms")
public class ShowroomController {

    private final ShowroomService service;

    public ShowroomController(ShowroomService service) {
        this.service = service;
    }

    /*
      BASIC CRUD
     */

    @PostMapping
    public ResponseEntity<ShowroomDTO> createShowroom(@RequestBody ShowroomDTO dto) {
        return service.createShowroom(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowroomDTO> getShowroomById(@PathVariable Long id) {
        return service.getShowroomById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ShowroomDTO>> getAllShowrooms() {
        return service.getAllShowrooms()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowroomDTO> updateShowroom(@PathVariable Long id, @RequestBody ShowroomDTO dto) {
        return service.updateShowroom(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteShowroom(@PathVariable Long id) {
        return service.deleteShowroom(id)
                .map(result -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    /*
      SEARCH / FILTER
     */

    @GetMapping("/search/location")
    public ResponseEntity<List<ShowroomDTO>> searchByLocation(@RequestParam String keyword) {
        return service.searchShowroomsByLocation(keyword)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/search/manager")
    public ResponseEntity<List<ShowroomDTO>> searchByManagerKeyword(@RequestParam String keyword) {
        return service.searchShowroomsByManagerKeyword(keyword)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/manager")
    public ResponseEntity<List<ShowroomDTO>> getByManagerName(@RequestParam String name) {
        return service.getShowroomsByManagerName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/with-cars")
    public ResponseEntity<List<ShowroomDTO>> getShowroomsWithCars() {
        return service.getShowroomsWithCars()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/with-staff")
    public ResponseEntity<List<ShowroomDTO>> getShowroomsWithStaff() {
        return service.getShowroomsWithStaff()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/active")
    public ResponseEntity<List<ShowroomDTO>> getActiveShowrooms() {
        return service.getActiveShowrooms()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
