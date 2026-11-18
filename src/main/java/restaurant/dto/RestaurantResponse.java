package com.example.booking.restaurant.dto;

import com.example.booking.restaurant.Restaurant;
import java.time.LocalTime;

public record RestaurantResponse(Long id, String name, String address,
                                 LocalTime openTime, LocalTime closeTime) {

    public static RestaurantResponse from(Restaurant r) {
        return new RestaurantResponse(r.getId(), r.getName(), r.getAddress(),
                r.getOpenTime(), r.getCloseTime());
    }
}