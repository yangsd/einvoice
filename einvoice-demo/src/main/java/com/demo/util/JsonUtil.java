package com.demo.util;

import com.alibaba.fastjson.JSON;

/**
 * Created by sdyang on 2016/10/8.
 */
public class JsonUtil {

    public static <T> T parseObject(String json,Class<T> clazz){
        return JSON.parseObject(json, clazz);
    }

    public static String toJSONString(Object object){
        return JSON.toJSONString(object);
    }
}
