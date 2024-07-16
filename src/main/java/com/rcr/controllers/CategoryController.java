package com.rcr.controllers;

import com.rcr.model.Category;
import com.rcr.model.User;
import com.rcr.services.CategoryService;
import com.rcr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @PostMapping("/admin/category/{restaurantId}")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                   @PathVariable Long restaurantId,
                                                   @RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        Category createdCategory = categoryService.createCategory(category.getName(), restaurantId);

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/category/restaurant/{restaurantId}")
    public ResponseEntity<List<Category>> findRestaurantCategory(
            @PathVariable Long restaurantId,
            @RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        List<Category> categories = categoryService.findCategoryByRestaurantId(restaurantId);

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
