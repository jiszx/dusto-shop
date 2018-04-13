package com.hhnz.util;

/**
 * Created by 杨 on 2017/1/10.
 */
public class WebSystemContext {
    //定义两个threadLocal变量：offSet和pageSize
    private static ThreadLocal<Integer> offset = new ThreadLocal<Integer>();
    private static ThreadLocal<Integer> limit = new ThreadLocal<Integer>();
    /*
     * offset ：get、set、remove
     */
    public static int getOffSet() {
        Integer os =offset.get();
        if (os == null) {
            return 0;
        }
        return os;
    }
    public static void setOffSet(int offSetValue) {
        offset.set(offSetValue);
    }
    public static void removeOffSet(){
        offset.remove();
    }
    /*
     * pageSize ：get、set、remove
     */
    public static int getPageSize() {
        Integer ps = limit.get();
        if (ps == null) {
            return 0;
        }
        return ps;
    }
    public static void setLimit(int limits) {
        limit.set(limits);
    }
    public static void removeLimit(){
        limit.remove();
    }
}
