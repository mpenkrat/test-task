package com.smarthosts.test.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Price implements Comparable<Price>{
    private BigDecimal amount;
    private Currency currency;

    @Override
    public int compareTo(Price price) {
        return amount.compareTo(price.getAmount());
    }
}
