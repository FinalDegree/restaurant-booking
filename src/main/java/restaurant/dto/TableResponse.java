package com.example.booking.restaurant.dto;

import com.example.booking.restaurant.DiningTable;

public record TableResponse(Long id, int tableNumber, int capacity) {

    public static TableResponse from(DiningTable t) {
        return new TableResponse(t.getId(), t.getTableNumber(), t.getCapacity());
    }
}