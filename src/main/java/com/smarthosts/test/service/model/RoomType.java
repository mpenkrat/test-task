package com.smarthosts.test.service.model;

public enum RoomType {
    PREMIUM("Premium"), ECONOMY("Economy");
    private String type;
    RoomType(final String type) {
        this.type = type;
    }
}
