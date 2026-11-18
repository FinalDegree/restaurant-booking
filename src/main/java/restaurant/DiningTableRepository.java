package com.example.booking.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiningTableRepository extends JpaRepository<DiningTable, Long> {

    List<DiningTable> findByRestaurantIdOrderByTableNumber(Long restaurantId);

    boolean existsByRestaurantIdAndTableNumber(Long restaurantId, int tableNumber);
}