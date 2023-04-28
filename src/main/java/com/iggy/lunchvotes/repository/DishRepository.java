package com.iggy.lunchvotes.repository;

import com.iggy.lunchvotes.model.restaurant.Dish;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

}