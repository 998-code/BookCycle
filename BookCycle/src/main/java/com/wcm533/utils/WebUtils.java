package com.wcm533.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @ClassName WebUtils
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2020/9/13 14:17
 **/
public class WebUtils {
    public static <T> T copyParamToBean(T bean,Map value){
        try {
            BeanUtils.populate(bean,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
    public static int parseInt(String strInt,int a){
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return a;
    }

}
