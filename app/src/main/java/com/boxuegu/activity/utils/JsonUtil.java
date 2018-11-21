package com.boxuegu.activity.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * @ClassName JsonUtil
 * @Author One_llx
 * @Date 2018/11/13 0013 下午 9:02
 * @Version 1.0
 */
public class JsonUtil {
    private final static Gson gson=new Gson();

    /**
     * 将json结果集转化为对象
     * @param jsonStr
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T jsonToPojo(String jsonStr, Class<T> tClass) {
        try {
            return gson.fromJson(jsonStr, tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将对象转换成json字符串。
     * @param object
     * @return
     */
    public static String objectToJson(Object object) {
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     *  将json数据转换成pojo对象list
     * @param jsonData
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType){
        try {
            return  gson.fromJson(jsonData, new TypeToken<List<T>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * json转化为字符串
     * @param jsonData
     * @return
     */
    public static Map<String,Object> josnToMap(String jsonData){
        try {
            return  gson.fromJson(jsonData,Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
