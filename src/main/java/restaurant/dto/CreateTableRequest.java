package com.example.booking.restaurant.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record CreateTableRequest(
        @Min(1) int tableNumber,
        @Min(1) @Max(20) int capacity) {
}