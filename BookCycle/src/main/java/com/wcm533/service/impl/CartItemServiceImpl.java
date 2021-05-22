package com.wcm533.service.impl;

import com.wcm533.dao.BookMapper;
import com.wcm533.dao.CartItemMapper;
import com.wcm533.pojo.Book;
import com.wcm533.pojo.CartItem;
import com.wcm533.service.CartItemService;

import java.util.List;

/**
 * @ClassName CartItemServiceImpl
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/05/03 16:49
 **/
public class CartItemServiceImpl implements CartItemService {

    private CartItemMapper cartItemMapper;
    private BookMapper bookMapper;

    public void setCartItemMapper(CartItemMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public boolean addCartItem(CartItem cartItem) {
        CartItem cartItemByBookId = cartItemMapper.queryCartItemByBookId(cartItem.getBookId(), cartItem.getUserId());
        System.out.println(cartItemByBookId);
        if(cartItemByBookId!=null){
            return false;
        }
        return cartItemMapper.addCartItem(cartItem) > 0;
    }

    @Override
    public boolean addCartItems(List<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) {
            CartItem cartItemByBookId = cartItemMapper.queryCartItemByBookId(cartItem.getBookId(), cartItem.getUserId());
            if (cartItemByBookId != null) {
                break;
            }
            cartItemMapper.addCartItem(cartItem);
        }
        return true;
    }

    @Override
    public List<CartItem> queryCartItemByUserId(int userId) {

        return cartItemMapper.queryCartItemsByUserId(userId);
    }

}
