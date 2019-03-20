import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Connect2 {

    public static  void main(String args[]) throws IOException {
        String json="{\"TOKEN\":\"93756702be814225b5a87cffe41b07d7\",\"ICS\":{\"MESSAGE_ID\":\"WXBA4AI2019000911\",\"MESSAGE_FORMAT\":\"json\",\"MESSAGE_TYPE\":\"ICS_ORDERS\",\"COMPRRESS\":\"0\",\"SEND_DATE\":\"2019-03-19 10:20:06\",\"SEND_SYS\":\"OMS\",\"SENDER\":\"0001\",\"RECEIVE_SYS\":\"ICS\",\"RECEIVER\":\"00001\",\"REMARK\":\"\",\"DATA\":[{\"ORDER_INFO\":{\"ORDER_NO\":\"WXBA4AI2019000911\",\"TRUST_CUSTOMER_ID\":\"C83DB74F-4BC7-4E6C-B11B-B8A049B90796\",\"DELIVERY_CUSTOMER_ID\":\"58561940-E453-4BE6-A539-726647D8EAA5\",\"RECEIVING_CUSTOMER_ID\":\"58561940-E453-4BE6-A539-726647D8EAA5\",\"PAYMENT_SOLUTION\":\"CQS1809280001\",\"FREIGHT_FLOW\":\"001\",\"SERVICE_TYPE\":\"500\",\"PM_TEAM_ID\":\"6ABB73D7-7AB8-4B78-BE08-FFDF5C8DD5CF\",\"CUSTOMER_BU\":\"\",\"ORDER_RECIEVER\":\"D2C3A870-6419-4038-8606-C5E10CA7A172\",\"ORDER_MAKER\":\"D2C3A870-6419-4038-8606-C5E10CA7A172\",\"ORDER_OPERATOR\":\"D2C3A870-6419-4038-8606-C5E10CA7A172\",\"IS_URGENT\":\"1\",\"CNAME\":\"黄金\",\"ENAME\":\"gold\",\"ENAME_SUM\":1,\"CONTRACT_NO\":\"1111\",\"INVOICE_NO\":\"22222\",\"CUSTOM_ORDER_NO\":\"33333\",\"COUNT\":11,\"WEIGHT\":11,\"VOLUME\":111,\"VOLUME_WEIGHT\":18537,\"CHARGING_WEIGHT\":18537,\"TOTAL_MONEY\":1111,\"MONEY_TYPE\":\"502\",\"TRANSPORT_TYPE\":\"\",\"GOODS_ATTR\":\"101|105|111|108|103|102|106|104|109|107|110\",\"DATE_REQUIRED\":\"2019-03-19 10:11:00\",\"ORDER_GOODS_PACK\":[{\"ORDER_NO\":\"WXBA4AI2019000911\",\"COUNT\":11,\"PACKING_TYPE\":\"100\",\"X\":2,\"Y\":2,\"Z\":2,\"CAN_SUPER_POSITION\":\"200\",\"SUPER_POSITION_COUNT\":1,\"PIECE_WEIGHT\":1,\"IS_NON_STANDARD\":\"200\",\"IPPC\":\"1111\"}],\"ORDER_CARGO_VALUE\":[{\"ORDER_NO\":\"WXBA4AI2019000911\",\"CARGO_VALUE\":1111,\"currency\":\"300\"}]},\"ORDER_LOADING_BILL\":[{\"ORDER_NO\":\"WXBA4AI2019000911\",\"BILL_TYPE\":\"01\",\"CHILD_BILL_TYPE\":\"0101\",\"BILL_NO\":\"123\",\"CHILD_BILL_NO\":\"456\",\"TO_OFF_DATE\":\"2019-03-19 10:11:00\"}],\"ORDER_ICS_SERVICE\":{\"ORDER_NO\":\"WXBA4AI2019000911\",\"PROPOSER\":\"D2C3A870-6419-4038-8606-C5E10CA7A172\",\"DEPARTMENT_ID\":\"6ABB73D7-7AB8-4B78-BE08-FFDF5C8DD5CF\",\"INSURANT\":\"58561940-E453-4BE6-A539-726647D8EAA5\",\"GOODS_NAME\":\"黄金\",\"CARGO_TYPE\":\"0\",\"PACKAGE_NUMBER\":11,\"PACKAGE_WEIGHT\":11,\"ACTUAL_CARRIER\":\"3\",\"BEAR_FEES\":\"woa\",\"INTERNATIONAL_TRANSPORT_TYPE\":\"001\",\"INLAND_TRANSPORT_TYPE\":\"200002\",\"FROM_CITY\":\"无锡\",\"PICK_DATETIME\":\"2019-03-19 10:12:00\",\"TO_CITY\":\"香港\",\"FEE_COST_TYPE\":\"0\",\"SHIP_NAME\":\"MH\",\"SAIL_NUM\":\"MH730\",\"FLIGHT_NUM\":\"730\",\"TRANS_NO\":\"kk123\",\"CAR_NO\":\"123\",\"Y_HT_GOODS\":\"1\",\"POLICY_REQUIREMENTS\":\"1\",\"ADDITIONAL_INSURANCE_TYPE\":\"0\",\"ADDITIONAL_INSURANCE_NAME\":\"\"}}]}}";
        postMessage("http://172.16.1.92:9998/InsuranceOrder/ICSOrders",json);
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
