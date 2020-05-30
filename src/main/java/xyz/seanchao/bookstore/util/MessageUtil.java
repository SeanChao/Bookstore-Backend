package xyz.seanchao.bookstore.util;

import com.alibaba.fastjson.JSONObject;

public class MessageUtil {
    public static JSONObject makeMessage(int status, JSONObject jsonObject) {
        jsonObject.put("status", status);
        return jsonObject;
    }

    public static JSONObject makeMessage(JSONObject jsonObject) {
        return makeMessage(0, jsonObject);
    }

    public static JSONObject makeMessage(int status) {
        return makeMessage(status, new JSONObject());
    }

    public static JSONObject makeMessage(int status, String msg) {
        JSONObject message = new JSONObject();
        message.put("msg", msg);
        return makeMessage(status, message);
    }
}
