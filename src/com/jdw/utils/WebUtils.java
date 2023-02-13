package com.jdw.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {
    public static <T> T copyParameterToBean(Map value, T bean) {
        try {
            System.out.println("注入之前:" + bean);
            BeanUtils.populate(bean,value);
            System.out.println("注入之后:" + bean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bean;
    }

    public static int parseInt(String str,int defaultValue){
        if (str != null){
            return Integer.parseInt(str);
        }else {
            return defaultValue;
        }
    }

    public static double parseDouble(String str,double defaultValue){
        if (str != null){
            return Double.parseDouble(str);
        }else {
            return defaultValue;
        }
    }
}
