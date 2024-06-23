package com.rcr.services;

import com.rcr.model.Category;
import com.rcr.model.Restaurant;
import com.rcr.repository.CategoryRepository;
import com.rcr.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Override
    public Category createCategory(String name, Long id) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()){
            throw new Exception("Category not found..........");
        }
        return optionalCategory.get();
    }
}
