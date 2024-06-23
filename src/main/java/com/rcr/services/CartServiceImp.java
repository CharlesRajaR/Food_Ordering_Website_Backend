package com.rcr.services;

import com.rcr.model.Cart;
import com.rcr.model.CartItems;
import com.rcr.model.Food;
import com.rcr.model.User;
import com.rcr.repository.CartItemRepository;
import com.rcr.repository.CartRepository;
import com.rcr.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImp implements CartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private FoodService foodService;
    @Override
    public CartItems addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.findUserByJwToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        Food food = foodService.findFoodById(req.getFoodId());

        for(CartItems cartItems:cart.getItems()){
            if(cartItems.getFood().equals(food)){
                int newQuantity = cartItems.getQuantity()+req.getQuantity();
                return updateCartItemQuantity(cartItems.getId(),newQuantity);
            }
        }
        CartItems cartItem = new CartItems();
        cartItem.setFood(food);
        cartItem.setCart(cart);
        cartItem.setQuantity(req.getQuantity());
        cartItem.setIngredients(req.getIngredients());
        cartItem.setTotalPrice(req.getQuantity()*food.getPrice());

        CartItems savedCartItem = cartItemRepository.save(cartItem);
        cart.getItems().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItems updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItems> cartItemsOptional = cartItemRepository.findById(cartItemId);
        if(cartItemsOptional.isEmpty()){
            throw new Exception("cart item was not found......");
        }
        CartItems item = cartItemsOptional.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice()*quantity);

        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        Optional<CartItems> cartItemOptional= cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception(
                    "cart item was not found....."
            );
        }
        CartItems cartItem = cartItemOptional.get();
        cart.getItems().remove(cartItem);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total = 0L;
        for(CartItems cartItem: cart.getItems()){
            total += cartItem.getFood().getPrice()*cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long cartId) throws Exception {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if(cartOptional.isEmpty()){
            throw new Exception("cart was not found..........");
        }

        return cartOptional.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        Cart cart = cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = cartRepository.findByCustomerId(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
