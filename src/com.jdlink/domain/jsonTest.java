package com.jdlink.domain;

import com.alibaba.fastjson.JSONObject;

public class jsonTest {

    public static  void  main(String args[]){
        String json="{\"id\":\"20142344037\",\"name\":\"YH\"}";
        JSONObject jsStr = JSONObject.parseObject(json); //将字符串{“id”：1}
        System.out.println(jsStr);
        System.out.println(jsStr.get("id"));
    }
}
