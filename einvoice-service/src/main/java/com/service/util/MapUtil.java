package com.service.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sdyang on 2016/10/11.
 */
public class MapUtil {

    //将bean对象转换为map
    public static Map<String, String> beanToMap(Object entity) {
        Class<? extends Object> c = entity.getClass();
        Object fieldValue = null;
        String fieldName = null;
        Field[] fields = c.getDeclaredFields();
        Map<String, String> fieldMap = new HashMap<String, String>();
        for (Field field : fields) {
            fieldName = field.getName();
            if (field.getModifiers() == Modifier.PUBLIC) {
                try {
                    fieldValue = field.get(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                fieldValue = invokeGet(entity, fieldName);
            }
            fieldMap.put(fieldName, fieldValue == null ? "" : fieldValue.toString());
        }
        return fieldMap;
    }

    private static Object invokeGet(Object entity, String fieldName) {
        try {
            Method method = entity.getClass().getMethod(
                    "get" + fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase()));
            return method.invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
