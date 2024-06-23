package com.rcr.controllers;

import com.rcr.model.Cart;
import com.rcr.model.CartItems;
import com.rcr.model.User;
import com.rcr.request.AddCartItemRequest;
import com.rcr.request.UpdateCartItemRequest;
import com.rcr.services.CartService;
import com.rcr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @PutMapping("/cart/add")
    public ResponseEntity<CartItems> addItemToCart(@RequestBody AddCartItemRequest req,
                                                   @RequestHeader("Authorization") String jwt)throws Exception{
        CartItems cartItem = cartService.addItemToCart(req, jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItems> updateCartItemQuantity(@RequestBody UpdateCartItemRequest req,
                                                            @RequestHeader("Authorization") String jwt)throws Exception{
        CartItems cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(@PathVariable Long id,
                                               @RequestHeader("Authorization") String jwt)throws Exception{
        Cart cart = cartService.removeItemFromCart(id, jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        Cart cart = cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
