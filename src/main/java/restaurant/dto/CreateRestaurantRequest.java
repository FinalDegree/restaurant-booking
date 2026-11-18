package com.example.booking.restaurant.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalTime;

public record CreateRestaurantRequest(
        @NotBlank @Size(max = 120) String name,
        @NotBlank @Size(max = 255) String address,
        @NotNull LocalTime openTime,
        @NotNull LocalTime closeTime) {

    @AssertTrue(message = "closeTime must be after openTime")
    public boolean isCloseAfterOpen() {
        return openTime == null || closeTime == null || closeTime.isAfter(openTime);
    }
}