package com.smarthosts.test.service.impl;

import com.smarthosts.test.service.GuestService;
import com.smarthosts.test.service.model.GuestBid;
import com.smarthosts.test.service.model.Price;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static com.smarthosts.test.service.model.Currency.EUR;

@Service
public class MockGuestService implements GuestService {

    public List<GuestBid> getGuestBids() {

        return List.of(new GuestBid(UUID.randomUUID(), new Price(BigDecimal.valueOf(23), EUR)),
                new GuestBid(UUID.randomUUID(), new Price(BigDecimal.valueOf(45), EUR)),
                new GuestBid(UUID.randomUUID(), new Price(BigDecimal.valueOf(155), EUR)),
                new GuestBid(UUID.randomUUID(), new Price(BigDecimal.valueOf(374), EUR)),
                new GuestBid(UUID.randomUUID(), new Price(BigDecimal.valueOf(22), EUR)),
                new GuestBid(UUID.randomUUID(), new Price(BigDecimal.valueOf(99), EUR)),
                new GuestBid(UUID.randomUUID(), new Price(BigDecimal.valueOf(100), EUR)),
                new GuestBid(UUID.randomUUID(), new Price(BigDecimal.valueOf(101), EUR)),
                new GuestBid(UUID.randomUUID(), new Price(BigDecimal.valueOf(115), EUR)),
                new GuestBid(UUID.randomUUID(), new Price(BigDecimal.valueOf(209), EUR)));
    }

}
