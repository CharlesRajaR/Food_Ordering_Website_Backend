package com.rcr.services;

import com.rcr.model.Category;

import java.util.List;

public interface CategoryService {
    public Category createCategory(String name, Long id) throws Exception;
    public List<Category> findCategoryByRestaurantId(Long restaurantId)throws Exception;
    public Category findCategoryById(Long id)throws Exception;
}
