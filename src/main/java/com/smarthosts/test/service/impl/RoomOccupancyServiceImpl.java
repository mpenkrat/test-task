package com.smarthosts.test.service.impl;

import com.smarthosts.test.service.RoomOccupancyService;
import com.smarthosts.test.service.model.GuestBid;
import com.smarthosts.test.service.model.Price;
import com.smarthosts.test.service.model.RoomOccupancy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static com.smarthosts.test.service.model.RoomType.ECONOMY;
import static com.smarthosts.test.service.model.RoomType.PREMIUM;
import static com.smarthosts.test.service.model.Currency.EUR;
import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

@Slf4j
@Service
public class RoomOccupancyServiceImpl implements RoomOccupancyService {
    private static final Price PREMIUM_MIN = new Price(BigDecimal.valueOf(100), EUR);
    private final MockGuestService guestService;

    public RoomOccupancyServiceImpl(final MockGuestService guestService) {
        this.guestService = guestService;
    }

    public List<RoomOccupancy> getRoomOccupancy(final int economy, final int premium) {
        List<GuestBid> guestBids = guestService.getGuestBids();
        PriorityQueue<GuestBid> bidsMaxHeap = new PriorityQueue<>(comparing(GuestBid::getPrice,
                reverseOrder()));
        bidsMaxHeap.addAll(guestBids);

        int premiumOccupied = 0, economyOccupied = 0;
        List<GuestBid> acceptedPremiumBids = new ArrayList<>();
        List<GuestBid> acceptedEconomyBids = new ArrayList<>();

        while (!bidsMaxHeap.isEmpty())  {
            GuestBid bid = bidsMaxHeap.poll();
            Price total = bid.getPrice();
            if (total.compareTo(PREMIUM_MIN) >= 0) {
                if (premiumOccupied < premium) {
                    acceptedPremiumBids.add(bid);
                    premiumOccupied++;
                }
            } else {
                if (premiumOccupied < premium && bidsMaxHeap.size() > economy) {
                    acceptedPremiumBids.add(bid);
                    premiumOccupied++;
                } else if (economyOccupied < economy) {
                    acceptedEconomyBids.add(bid);
                    economyOccupied++;
                }
            }
        }

        List<RoomOccupancy> roomOccupancyList = new ArrayList<>();
        if (economy > 0) {
            roomOccupancyList.add(new RoomOccupancy(ECONOMY, economyOccupied, getTotal(acceptedEconomyBids)));
        }
        if (premium > 0) {
            roomOccupancyList.add(new RoomOccupancy(PREMIUM, premiumOccupied, getTotal(acceptedPremiumBids)));
        }
        return roomOccupancyList;
    }

    private Price getTotal(List<GuestBid> bids) {
        BigDecimal total = bids.stream().map(bid -> bid.getPrice().getAmount()).
                reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Price(total, EUR);
    }
}
