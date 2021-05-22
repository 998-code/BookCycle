package com.wcm533.utils;

import com.wcm533.pojo.CartItem;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName WebUtils
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2020/9/13 14:17
 **/
public class WebUtils<T> {
    public static <T> T copyParamToBean(T bean, Map value) {
        try {
            BeanUtils.populate(bean, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String strInt, int a) {
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return a;
    }

    public static Date parseString(String str) {
        String s = str.substring(0, 13);
        long aLong = Long.parseLong(s);
        return new Date(aLong);
    }

    public static boolean isNum(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static <E, K> List<E> MapToList(Map<K,E> map){
        List<E> values=new ArrayList<E>();
        Set<Map.Entry<K, E>> entries = map.entrySet();
        for (Map.Entry<K, E> next : entries) {
            values.add(next.getValue());
        }
        return values;
    }

    public static List<CartItem> MapToCartItemList(Map<Integer,CartItem> map,int userId){
        ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
        Set<Map.Entry<Integer, CartItem>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, CartItem>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, CartItem> next = iterator.next();
            CartItem value = next.getValue();
            value.setUserId(userId);
            cartItems.add(value);
        }
        return cartItems;
    }

}
