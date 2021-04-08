package com.ning.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ning
 * @create 2021/4/8 0008 18:27
 */
public class Msg {
    //状态码100-成功 200-失败
    private int code;
    //提示信息
    private String msg;
    //需要返回的数据
    private Map<String,Object> extend = new HashMap<String, Object>();

    private static Msg success(){


    }

    private static Msg fail(){


    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
