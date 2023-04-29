package com.iggy.lunchvotes.service;

import com.iggy.lunchvotes.model.restaurant.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    public Restaurant create(Restaurant restaurant){
        return null;
    }

    public Restaurant update(int id, Restaurant restaurant){
        return null;
    }

    public Optional<Restaurant> get(int id){
        return null;
    }
    public Restaurant getWithDishes(int id){
        return null;
    }

    public void delete(int id){

    }

    public List<Restaurant> getAll(){
        return null;
    }

    public List<Restaurant> getAllWithDishes(){
        return null;
    }

    public void setDishes(int id, List<Integer> ids){

    }

}
