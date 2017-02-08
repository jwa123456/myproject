package com.netty.util.memery;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yh on 17-2-7.
 */
public class JsonUtil {
    public static List<String> strToArray(String json) {
        return JSONArray.parseArray(json, String.class);
    }

    public static Map<String, Object> strToMap(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        Map<String, Object> params = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
        }
        return params;
    }

    public static String getData(String data, String flag) {

        return JSON.parseObject(data).get(flag).toString();
    }

    public static boolean isJson(String string) {
        try {
            JSON.parseObject(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static JSONArray listToJson(List list) {
        return JSON.parseArray(JSON.toJSONString(list, true));
    }

    public static String pojoToJson(Object object) {
        return JSON.toJSONString(object, true);
    }

    public static JSONObject str2Json(String str) {
        return JSON.parseObject(str);
    }

    public static JSONArray getArrayFromStr(String key, String json) {
        JSONObject object = JSON.parseObject(json);
        return (JSONArray) object.get(key);
    }

    public static JSONArray getArrayFromJSON(String key, JSONObject json) {
        return (JSONArray) json.get(key);
    }

    public static List getListByArray(String jsonString) {
        List personList = new ArrayList();
        JSONArray jsonArray = new JSONArray(2);
        System.out.println(jsonArray.size() + "长度");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            personList.add(jsonObject.get(i));
        }
        return personList;
    }

}
