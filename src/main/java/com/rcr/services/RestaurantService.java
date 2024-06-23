package com.rcr.services;

import com.rcr.dto.RestaurantDto;
import com.rcr.model.Restaurant;
import com.rcr.model.User;
import com.rcr.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception;
    public void deleteRestaurant(Long restaurantId) throws Exception;
    public List<Restaurant> getAllRestaurant();
    public List<Restaurant> searchRestaurant(String keyword);
    public Restaurant findRestaurantById(Long id) throws Exception;
    public Restaurant getRestaurantByUserId(Long userId) throws Exception;
    public RestaurantDto addToFavourites(Long restaurantId, User user)throws Exception;
    public Restaurant updateRestaurantStatus(Long id)throws Exception;
}
