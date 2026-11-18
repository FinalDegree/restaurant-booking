package com.example.booking.restaurant;

import jakarta.persistence.*;

@Entity
@Table(name = "dining_table")
public class DiningTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "table_number", nullable = false)
    private int tableNumber;

    @Column(nullable = false)
    private int capacity;

    protected DiningTable() {
        // нужен JPA
    }

    public DiningTable(Restaurant restaurant, int tableNumber, int capacity) {
        this.restaurant = restaurant;
        this.tableNumber = tableNumber;
        this.capacity = capacity;
    }

    public Long getId() { return id; }
    public Restaurant getRestaurant() { return restaurant; }
    public int getTableNumber() { return tableNumber; }
    public int getCapacity() { return capacity; }
}