package com.rcr.services;

import com.rcr.model.*;
import com.rcr.repository.AddressRepository;
import com.rcr.repository.OrderItemRepository;
import com.rcr.repository.OrderRepository;
import com.rcr.repository.UserRepository;
import com.rcr.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CartService cartService;

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(orderOptional.isEmpty()){
            throw new Exception("order was not found......");
        }
        return orderOptional.get();
    }

    @Override
    public Order createOrder(OrderRequest orderReq, User user) throws Exception {
        Address address = new Address();
        address.setDeliveryAddress(orderReq.getAddressRequest().getDeliveryAddress());
        address.setUser(user);
        Address savedAddress = addressService.createAddress(address);

        if(!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
        }
        userRepository.save(user);
        Restaurant restaurant = restaurantService.findRestaurantById(orderReq.getRestaurantId());
        Order createdOrder = new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("pending");
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setRestaurant(restaurant);

        Cart cart = cartService.findCartByUserId(user.getId());
        List<OrderItems> orderItems= new ArrayList<>();
        for (CartItems item: cart.getItems()) {
            OrderItems orderItem = new OrderItems();
            orderItem.setFood(item.getFood());
            orderItem.setIngredient(item.getIngredients());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setTotalPrice(item.getTotalPrice());

            OrderItems savedOderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOderItem);
        }
        createdOrder.setItems(orderItems);
        Long totalPrice = cartService.calculateCartTotals(cart);
        createdOrder.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(createdOrder);
        restaurant.getOrder().add(savedOrder);
        return savedOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED") ||
                orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")){
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("please select valid order status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception {

        return orderRepository.findByCustomerId(userId);

    }

    @Override
    public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
        if(orderStatus != null){
            orders.stream()
                    .filter(order -> order.getOrderStatus().equals(orderStatus))
                    .collect(Collectors.toList());
        }
        return orders;
    }
}