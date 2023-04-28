package com.iggy.lunchvotes.repository;

import com.iggy.lunchvotes.model.restaurant.Restaurant;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

}