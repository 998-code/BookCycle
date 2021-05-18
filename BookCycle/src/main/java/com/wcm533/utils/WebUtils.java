package com.wcm533.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName WebUtils
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2020/9/13 14:17
 **/
public class WebUtils {
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
        Long aLong = Long.parseLong(s);
        Date date = new Date(aLong);
        return date;
    }

    public static boolean isNum(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
