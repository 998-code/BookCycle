package com.wcm533.service;

import com.wcm533.pojo.CartItem;

import java.util.List;

/**
 * @ClassName CartItemService
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/05/03 16:48
 **/
public interface CartItemService {

    boolean addCartItem(CartItem cartItem);

    boolean addCartItems(List<CartItem> cartItems);

    List<CartItem> queryCartItemByUserId(int userId);
}
