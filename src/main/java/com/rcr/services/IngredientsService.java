package com.rcr.services;

import com.rcr.model.IngredientsCategory;
import com.rcr.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {
    public IngredientsCategory createIngredientCategory(String name, Long restaurantId)throws Exception;
    public IngredientsCategory findIngredientCategoryById(Long id)throws Exception;
    public List<IngredientsCategory> findIngredientCategoryByRestaurantId(Long id)throws Exception;
    public IngredientsItem createIngredientItem(Long restaurantId, String name, Long categoryId)throws Exception;
    public List<IngredientsItem> findRestaurantIngredientItem(long restaurantId)throws Exception;
    public IngredientsItem updateStock(Long id)throws Exception;
}
