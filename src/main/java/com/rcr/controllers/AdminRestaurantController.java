package com.rcr.controllers;

import com.rcr.model.Restaurant;
import com.rcr.model.User;
import com.rcr.response.MessageResponse;
import com.rcr.services.RestaurantService;
import com.rcr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rcr.request.CreateRestaurantRequest;


@RestController
@RequestMapping("api/admin/restaurants")
public class AdminRestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;
    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader ("Authorization") String jwt)throws Exception{
           User user = userService.findUserByJwToken(jwt);
           Restaurant restaurant = restaurantService.createRestaurant(req, user);

           return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader ("Authorization") String jwt,
            @PathVariable Long id)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurant(id, req);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestHeader ("Authorization") String jwt,
            @PathVariable Long id)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        restaurantService.deleteRestaurant(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("restaurant deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(
            @RequestHeader ("Authorization") String jwt,
            @PathVariable Long id)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);


        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId(
            @RequestHeader ("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());


        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


}





