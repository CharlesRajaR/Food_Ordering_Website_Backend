package com.rcr.services;

import com.rcr.model.Order;
import com.rcr.model.User;
import com.rcr.request.OrderRequest;

import java.util.List;

public interface OrderService {
    public Order findOrderById(Long orderId)throws Exception;
    public Order createOrder(OrderRequest order, User user) throws Exception;
//    orderStatus = pending , completed.,
    public Order updateOrder(Long orderId, String orderStatus)throws Exception;
    public void cancelOrder(Long orderId)throws Exception;
    public List<Order> getUsersOrder(Long userId)throws Exception;
    public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus);
}
