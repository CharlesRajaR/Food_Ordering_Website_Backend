package com.rcr.services;

import com.rcr.model.Category;
import com.rcr.model.IngredientsCategory;
import com.rcr.model.IngredientsItem;
import com.rcr.model.Restaurant;
import com.rcr.repository.IngredientsCategoryRepository;
import com.rcr.repository.IngredientsItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImp implements IngredientsService{
    @Autowired
    private IngredientsItemRepository ingredientsItemRepository;
    @Autowired
    private IngredientsCategoryRepository ingredientsCategoryRepository;
    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientsCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientsCategory ingredientsCategory = new IngredientsCategory();
        ingredientsCategory.setRestaurant(restaurant);
        ingredientsCategory.setName(name);

        return ingredientsCategoryRepository.save(ingredientsCategory);
    }

    @Override
    public IngredientsCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientsCategory> opt = ingredientsCategoryRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("mentioned ingredient category was not found....");
        }
        return opt.get();
    }

    @Override
    public List<IngredientsCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientsCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String name, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientsCategory  category = findIngredientCategoryById(categoryId);

        IngredientsItem item = new IngredientsItem();
        item.setName(name);
        item.setCategory(category);
        item.setRestaurant(restaurant);

        IngredientsItem ingredient = ingredientsItemRepository.save(item);
        category.getIngredients().add(ingredient);

        return ingredient;
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredientItem(long restaurantId) throws Exception {
        
        return ingredientsItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> ingredientsItemOptional = ingredientsItemRepository.findById(id);
        if(ingredientsItemOptional.isEmpty()){
            throw new Exception("ingrdient item was not found.......");
        }
        IngredientsItem ingredientsItem = ingredientsItemOptional.get();
        ingredientsItem.setInStock(!ingredientsItem.isInStock());
        return ingredientsItemRepository.save(ingredientsItem);
    }
}
