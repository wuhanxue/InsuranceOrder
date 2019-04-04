import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Connect2 {

    public static  void main(String args[]) throws IOException {
        String json="{\"OMS\":{\"SEND_DATE\":\"2019-04-02 15:27:29\",\"SENDER\":\"0001\",\"MESSAGE_ID\":\"53fe3459-130c-474c-b002-225d2dc13d18\",\"MESSAGE_FORMAT\":\"json\",\"COMPRRESS\":\"0\",\"MESSAGE_TYPE\":\"INSURANCE_DT\",\"RECEIVER\":\"00001\",\"SEND_SYS\":\"ICS\",\"REMARK\":\"备注\",\"DATA\":[{\"INSURANCE_DT\":[{\"POLICY_NO\":\"003\",\"DEF5\":\"\",\"INSURANCE_DATE\":\"2019-03-15 00:00:00\",\"DATASTATUS\":\"1\",\"ORDER_NO\":\"20190221002\",\"DEF2\":\"\",\"DEF1\":\"\",\"DEF4\":\"\",\"DEF3\":\"\",\"INSURANCE_COMPANY\":\"保险3\",\"FEE\":\"12.33\"}]}],\"RECEIVE_SYS\":\"OMS\"},\"TOKEN\":\"cf5705a9188540e5a5dba851221c10c0\"}";
        postMessage("http://172.16.1.92:9998/InsuranceOrder/PushInsuranceDetail",json);
    }
    /*推送信息到云平台接口*/
    public static  void postMessage(String url,String json) throws IOException {

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        //构建一个json格式字符串textMsg，其内容是接收方需要的参数和消息内容
        String textMsg = json;
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = org.apache.http.util.EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("结果"+result);
        }

    }
}
