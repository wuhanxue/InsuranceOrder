package com.jdlink.domain;

import net.sf.json.JSONObject;

public class CreateJsonObject {

    public static void main(String args[]){
        JSONObject jsonObject=new JSONObject();
        JSONObject lan1=new JSONObject();
        lan1.put("MESSAGE_ID","原报文发送方消息唯一ID");
        lan1.put("MESSAGE_TYPE","消息类型");
        lan1.put("DATE","回执时间");
        lan1.put("REMARK","其他备注信息");
        jsonObject.put("RECEIVE_SYS","回执接收系统");
        jsonObject.put("SEND_SYS","回执发送系统");
        jsonObject.put("RESULTFLAG","1");
        jsonObject.put("FAILCODE","");
        jsonObject.put("FAILINFO","");
        jsonObject.put("RETDATA",lan1);
        System.out.println(jsonObject.toString());
    }
}
