package com.smarthosts.test.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GuestBid {
    private UUID guestBidId;
    private Price price;
}
