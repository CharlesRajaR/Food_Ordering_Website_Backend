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
@RequestMapping("api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String jwt,
                                                       @PathVariable Long id,
                                                       @RequestParam(required = false) String orderStatus)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        List<Order> orders = orderService.getRestaurantOrder(id, orderStatus);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(@RequestHeader("Authorization") String jwt,
                                                       @PathVariable String orderStatus,
                                                       @PathVariable Long id)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        Order order = orderService.updateOrder(id, orderStatus);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
