package com.rcr.controllers;

import com.rcr.model.Order;
import com.rcr.model.User;
import com.rcr.request.OrderRequest;
import com.rcr.services.OrderService;
import com.rcr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestHeader String jwt,
                                             @RequestBody OrderRequest req)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        Order order = orderService.createOrder(req, user);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader String jwt)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        List<Order> orders = orderService.getUsersOrder(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
