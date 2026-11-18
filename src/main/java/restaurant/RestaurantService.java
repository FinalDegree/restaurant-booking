package com.example.booking.restaurant;

import com.example.booking.common.ConflictException;
import com.example.booking.common.NotFoundException;
import com.example.booking.restaurant.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final DiningTableRepository tableRepository;

    public RestaurantService(RestaurantRepository restaurantRepository,
                             DiningTableRepository tableRepository) {
        this.restaurantRepository = restaurantRepository;
        this.tableRepository = tableRepository;
    }

    @Transactional
    public RestaurantResponse create(CreateRestaurantRequest request) {
        Restaurant restaurant = new Restaurant(request.name(), request.address(),
                request.openTime(), request.closeTime());
        return RestaurantResponse.from(restaurantRepository.save(restaurant));
    }

    public List<RestaurantResponse> findAll() {
        return restaurantRepository.findAll().stream()
                .map(RestaurantResponse::from)
                .toList();
    }

    public RestaurantResponse get(Long id) {
        return RestaurantResponse.from(getRestaurant(id));
    }

    @Transactional
    public TableResponse addTable(Long restaurantId, CreateTableRequest request) {
        Restaurant restaurant = getRestaurant(restaurantId);
        if (tableRepository.existsByRestaurantIdAndTableNumber(restaurantId, request.tableNumber())) {
            throw new ConflictException("Table %d already exists in this restaurant"
                    .formatted(request.tableNumber()));
        }
        DiningTable table = new DiningTable(restaurant, request.tableNumber(), request.capacity());
        return TableResponse.from(tableRepository.save(table));
    }

    public List<TableResponse> getTables(Long restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new NotFoundException("Restaurant %d not found".formatted(restaurantId));
        }
        return tableRepository.findByRestaurantIdOrderByTableNumber(restaurantId).stream()
                .map(TableResponse::from)
                .toList();
    }

    private Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurant %d not found".formatted(id)));
    }
}