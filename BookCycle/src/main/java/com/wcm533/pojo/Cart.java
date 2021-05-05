package com.wcm533.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName Cart
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 15:32
 **/
public class Cart {

    public static final int ORDINARY_MEMBER_TOTAL_COUNT=5;
    public static final int SUPER_MEMBER_TOTAL_COUNT=10;

    public static final int SUCCESS=0;//成功
    public static final int NOT_EXISTED=1;//图书不存在
    public static final int ADDED=2;//已添加
    public static final int INSUFFICIENT_PERMISSIONS=3;//权限不足

    private Map<Integer,CartItem> items=new LinkedHashMap<Integer, CartItem>();

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        //从Map集合中获取item
        CartItem item = items.get(cartItem.getBookId());
        //判断是否存在item
        if(item==null){
            //item不存在  --添加新的商品项
            items.put(cartItem.getBookId(),cartItem);
        }else{
            //item存在  --将item中商品数量加一，设置总价
            item.setCount(item.getCount()+1);
            item.setTotalPoints(item.getPoints()*item.getCount());
        }
    }

    /**
     * 删除商品项
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void updateItem(Integer id,Integer count){
        CartItem cartItem = items.get(id);
        if(cartItem!=null){
            //设置商品总数，总价
            cartItem.setCount(count);
            cartItem.setTotalPoints(cartItem.getPoints()*cartItem.getCount());
        }
    }

    public Cart() {
    }

    public Integer getTotalCount() {
        Integer totalCount =0;
        for(CartItem item:items.values()){
            totalCount+=item.getCount();
        }
        return totalCount;
    }

    public int getTotalPrice() {
        int totalPrice =0;
        for(CartItem item:items.values()){
            totalPrice=totalPrice+item.getTotalPoints();
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
