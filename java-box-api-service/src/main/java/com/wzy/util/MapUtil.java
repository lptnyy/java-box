package com.wzy.util;

import org.apache.commons.beanutils.BeanMap;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class MapUtil {

    /**
     * 将对象转成map类型
     * @param obj
     * @return
     */
    public static Map<?,?> objectToMap(Object obj) {
        if (obj==null){
            return null;
        }
        return new BeanMap(obj);
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
                 if (map == null)
                         return null;
        Object obj = null;
        try {
            obj = beanClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
