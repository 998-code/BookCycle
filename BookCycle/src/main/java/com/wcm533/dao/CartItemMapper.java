package com.wcm533.dao;

import com.wcm533.pojo.CartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName CartItemMapper
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/05/03 16:13
 **/
public interface CartItemMapper {

    int addCartItem(CartItem cartItem);

    int deleteCartItem(@Param("bookId") int bookId,@Param("userId") int userId);

    int updateCartItem(CartItem cartItem);

    CartItem queryCartItemByBookId(@Param("bookId") int bookId,@Param("userId") int userId);

    List<CartItem> queryCartItemsByUserId(@Param("userId") int userId);
}
