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

    /**
     * 新增
     * @param cartItem
     * @return
     */
    int addCartItem(CartItem cartItem);

    /**
     * 删除
     * @param bookId
     * @param userId
     * @return
     */
    int deleteCartItem(@Param("bookId") int bookId,@Param("userId") int userId);

    /**
     * 修改
     * @param cartItem
     * @return
     */
    int updateCartItem(CartItem cartItem);

    /**
     * 根查询该用户是否已经将该图书加入借阅书单
     * @param bookId
     * @param userId
     * @return
     */
    CartItem queryCartItemByBookId(@Param("bookId") int bookId,@Param("userId") int userId);

    /**
     * 根据用户id查询该用户加入借阅书单的所有图书
     * @param userId
     * @return
     */
    List<CartItem> queryCartItemsByUserId(@Param("userId") int userId);
}
