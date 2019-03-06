package com.jdlink.domain;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ChatbotSend {
    //WebHook地址xxx
    public static String WEBHOOK_TOKEN = "https://edi.jd-link.cn/Api/PushOperationTracking";

    public static void main(String args[]) throws Exception {
        org.apache.http.client.HttpClient httpclient = org.apache.http.impl.client.HttpClients.createDefault();
        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        //构建一个json格式字符串textMsg，其内容是接收方需要的参数和消息内容
        String textMsg = "{\"msgtype\":\"text\",\"text\":{\"content\":\"你好，我是机器人\"},\"at\":{\"atMobiles\":[\"xxx\"],\"isAtAll\":false}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
    }
}
