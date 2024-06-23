package com.rcr.services;

import com.rcr.model.Food;
import com.rcr.model.Category;
import com.rcr.model.Restaurant;
import com.rcr.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
    void deleteFood(Long foodId)throws Exception;
    public List<Food> restaurantsFood(Long restaurantId, boolean isVegetarian, boolean isSeasonal, boolean isNonVeg, String foodCategory);
    public List<Food> searchFood(String keyword);
    public Food findFoodById(Long foodId) throws Exception;
    public Food updateAvailabilityStatus(Long foodId) throws Exception;
}
