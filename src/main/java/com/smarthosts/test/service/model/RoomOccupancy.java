package com.smarthosts.test.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomOccupancy {
    private RoomType roomType;
    private int roomsCount;
    private Price total;
}
