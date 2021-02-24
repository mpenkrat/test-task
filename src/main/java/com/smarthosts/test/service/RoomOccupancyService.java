package com.smarthosts.test.service;

import com.smarthosts.test.service.model.RoomOccupancy;

import java.util.List;

public interface RoomOccupancyService {
    List<RoomOccupancy> getRoomOccupancy(final int economy, final int premium);
}
