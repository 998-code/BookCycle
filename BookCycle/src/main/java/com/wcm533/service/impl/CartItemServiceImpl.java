package com.wcm533.service.impl;

import com.wcm533.dao.CartItemMapper;
import com.wcm533.pojo.CartItem;
import com.wcm533.service.CartItemService;

/**
 * @ClassName CartItemServiceImpl
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/05/03 16:49
 **/
public class CartItemServiceImpl implements CartItemService {

    private CartItemMapper cartItemMapper;

    public void setCartItemMapper(CartItemMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public boolean addCartItem(CartItem cartItem) {
        CartItem cartItemByBookId = cartItemMapper.queryCartItemByBookId(cartItem.getBookId(), cartItem.getUserId());
        if(cartItemByBookId!=null){
            return false;
        }
        if (cartItemMapper.addCartItem(cartItem)>0){
            return true;
        }
        return false;
    }
}
