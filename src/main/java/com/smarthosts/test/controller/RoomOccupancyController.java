package com.smarthosts.test.controller;

import com.smarthosts.test.service.RoomOccupancyService;
import com.smarthosts.test.service.model.RoomOccupancy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/rooms/occupancy")
public class RoomOccupancyController {
    private final RoomOccupancyService roomOccupancyService;

    public RoomOccupancyController(RoomOccupancyService roomOccupancyService) {
        this.roomOccupancyService = roomOccupancyService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getRoomOccupancy(@RequestParam(required = false, defaultValue = "0")
                   @Min(value = 0, message = "Economy value must be greater than or equal to 0") Integer economy,
                                              @RequestParam(required = false, defaultValue = "0")
                   @Min(value = 0, message = "Premium value must be greater than or equal to 0") Integer premium) {
        List<RoomOccupancy> roomOccupancyList = roomOccupancyService.getRoomOccupancy(economy, premium);
        return ResponseEntity.ok(roomOccupancyList);
    }

    @ExceptionHandler
    public ResponseEntity<?> constraintViolationHandler(ConstraintViolationException ex) {
        return ResponseEntity.badRequest()
                .body(Map.of("errors", ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)));
    }
}
