package com.hhnz.util;

import org.apache.ibatis.mapping.BoundSql;

import java.lang.reflect.Field;


/**
 * Created by 杨 on 2017/1/11.
 */
public class ReflectHelper {

    public static void setFieldValue(Object boundSql, String fieldName, Object params) {
        try{
            Field field = boundSql.getClass().getField(fieldName);
            field.setAccessible(true);
            field.set(boundSql,params);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
