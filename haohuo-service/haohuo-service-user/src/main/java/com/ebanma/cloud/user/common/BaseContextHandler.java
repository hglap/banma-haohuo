package com.ebanma.cloud.user.common;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class BaseContextHandler {
    private BaseContextHandler() {

    }

    public static final String CONTEXT_KEY_USERAID = "currentUserAid";
    public static final String CONTEXT_KEY_PHONE = "currentPhone";
    private static final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key){
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static String getUserId(){
        Object value = get(CONTEXT_KEY_USERAID);
        return returnObjectValue(value);
    }

    public static String getPhone(){
        Object value = get(CONTEXT_KEY_PHONE);
        return returnObjectValue(value);
    }

    public static void setUserId(String userAid){
        set(CONTEXT_KEY_USERAID,userAid);
    }

    public static void setPhone(String phone){
        set(CONTEXT_KEY_PHONE,phone);
    }


    private static String returnObjectValue(Object value) {
        return value==null?null:value.toString();
    }

    public static void remove(){
        threadLocal.remove();
    }

}
