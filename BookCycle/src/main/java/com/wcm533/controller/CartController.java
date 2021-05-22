package com.wcm533.controller;

import com.wcm533.pojo.Book;
import com.wcm533.pojo.Cart;
import com.wcm533.pojo.CartItem;
import com.wcm533.pojo.User;
import com.wcm533.service.impl.BookServiceImpl;
import com.wcm533.service.impl.CartItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @ClassName CartController
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/05/01 12:56
 **/

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    @Qualifier("bookServiceImpl")
    private BookServiceImpl bookService;

    @Autowired
    @Qualifier("cartItemServiceImpl")
    private CartItemServiceImpl cartItemService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/getCart")
    public String getCart(){
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            Cart cart = new Cart();
            List<CartItem> cartItems = cartItemService.queryCartItemByUserId(user.getId());
            for (CartItem cartItem : cartItems) {
                cart.addItem(cartItem);
            }
            request.getSession().setAttribute("cart",cart);
        }
        return "cart/book_cart";
    }

    @RequestMapping(value = "/addItem", produces = "application/json; charset=utf-8")
    @ResponseBody
    public int addItem(int bookId){
        Book book = bookService.queryBookById(bookId);
        if(book==null){
            return Cart.NOT_EXISTED;
        }
        if(book.getLoan()==book.getStock()){
            return Cart.INVENTORY_SHORTAGE;
        }
        User user = (User) request.getSession().getAttribute("user");
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPoints(),book.getPoints(),0);
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        if (user!=null){
            cartItem.setUserId(user.getId());
            cartItemService.addCartItem(cartItem);
        }
        cart.addItem(cartItem);
        return Cart.SUCCESS;
    }

}
