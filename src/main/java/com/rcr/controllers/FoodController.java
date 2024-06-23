package com.rcr.controllers;

import com.rcr.model.Food;
import com.rcr.model.Restaurant;
import com.rcr.model.User;
import com.rcr.request.CreateFoodRequest;
import com.rcr.services.FoodService;
import com.rcr.services.RestaurantService;
import com.rcr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;
    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                                 @RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        List<Food> foods = foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@PathVariable Long restaurantId,
                                                 @RequestParam boolean vegetarian,
                                                 @RequestParam boolean seasonal,
                                                 @RequestParam boolean nonVeg,
                                                 @RequestParam(required = false) String foodCategory,
                                                 @RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        List<Food> foods = foodService.restaurantsFood(restaurantId, vegetarian, seasonal, nonVeg, foodCategory);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
