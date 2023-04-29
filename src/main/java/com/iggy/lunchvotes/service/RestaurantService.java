package com.iggy.lunchvotes.service;

import com.iggy.lunchvotes.model.restaurant.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant create();

    Restaurant update();

    Restaurant get();
    Restaurant getWithDishes();

    void delete();

    List<Restaurant> getAll();

    List<Restaurant> getAllWithDishes();

    void setDishes();

}
