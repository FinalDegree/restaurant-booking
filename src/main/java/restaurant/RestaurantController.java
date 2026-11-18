package com.example.booking.restaurant;

import com.example.booking.restaurant.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RestaurantResponse> create(@Valid @RequestBody CreateRestaurantRequest request) {
        RestaurantResponse created = service.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(created.id()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public List<RestaurantResponse> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RestaurantResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping("/{id}/tables")
    public ResponseEntity<TableResponse> addTable(@PathVariable Long id,
                                                  @Valid @RequestBody CreateTableRequest request) {
        TableResponse created = service.addTable(id, request);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}/tables")
    public List<TableResponse> tables(@PathVariable Long id) {
        return service.getTables(id);
    }
}