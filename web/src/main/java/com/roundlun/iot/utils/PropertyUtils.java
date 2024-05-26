package com.roundlun.iot.utils;

import com.alibaba.fastjson.JSONObject;
import com.roundlun.iot.domain.entity.SysProperty;

import java.util.Date;

public class PropertyUtils {

    public static Object getValue(SysProperty property, JSONObject jsonObject) {
        if (jsonObject.containsKey(property.getPropertyKey())) {
            if (property.getPropType().getPropTypeName().equals("int")) {
                return jsonObject.getInteger(property.getPropertyKey());
            }
            if (property.getPropType().getPropTypeName().equals("float")) {
                return jsonObject.getFloat(property.getPropertyKey());
            }
            if (property.getPropType().getPropTypeName().equals("double")) {
                return jsonObject.getDouble(property.getPropertyKey());
            }
            if (property.getPropType().getPropTypeName().equals("bool")) {
                return jsonObject.getBoolean(property.getPropertyKey());
            }
            if (property.getPropType().getPropTypeName().equals("text")) {
                return jsonObject.getString(property.getPropertyKey());
            }
            if (property.getPropType().getPropTypeName().equals("date")) {
                return DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", new Date(jsonObject.getLong(property.getPropertyKey()) * 1000));
            }
        }
        return null;
    }

}
