package com.jdlink.domain;







import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Connect {
    /*推送信息到云平台接口*/
    public static  void postMessage(String url,String json) throws IOException {

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        //构建一个json格式字符串textMsg，其内容是接收方需要的参数和消息内容
        String textMsg = json;
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
        org.apache.http.HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = org.apache.http.util.EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }

    }
}
