package com.jdlink.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jdlink.domain.dataItem.CurrencyDataItem;
import com.jdlink.domain.dataItem.DepartmentDataItem;
import com.jdlink.domain.dataItem.FreightDataItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.jdlink.controller.Util.getJSONObject1;

public class JsonToJava {

    public static void main(String args[]) throws ParseException {
        String json="{\"TOKEN\":\"b297f26965774abf9405ab471c679d85\",\"ICS\":{\"MESSAGE_ID\":\"WXBA4AI2019000930\",\"MESSAGE_FORMAT\":\"json\",\"MESSAGE_TYPE\":\"ICS_ORDERS\",\"COMPRRESS\":\"0\",\"SEND_DATE\":\"2019-03-27 11:01:33\",\"SEND_SYS\":\"OMS\",\"SENDER\":\"0001\",\"RECEIVE_SYS\":\"ICS\",\"RECEIVER\":\"00001\",\"REMARK\":\"\",\"DATA\":[{\"ORDER_INFO\":[{\"ORDER_NO\":\"WXBA4AI2019000930\",\"TRUST_CUSTOMER_ID\":\"C83DB74F-4BC7-4E6C-B11B-B8A049B90796\",\"DELIVERY_CUSTOMER_ID\":\"\",\"RECEIVING_CUSTOMER_ID\":\"58561940-E453-4BE6-A539-726647D8EAA5\",\"PAYMENT_SOLUTION\":\"CQS1809280001\",\"FREIGHT_FLOW\":\"001\",\"SERVICE_TYPE\":\"500\",\"PM_TEAM_ID\":\"6ABB73D7-7AB8-4B78-BE08-FFDF5C8DD5CF\",\"CUSTOMER_BU\":\"\",\"ORDER_RECIEVER\":\"D2C3A870-6419-4038-8606-C5E10CA7A172\",\"ORDER_MAKER\":\"D2C3A870-6419-4038-8606-C5E10CA7A172\",\"ORDER_OPERATOR\":\"D2C3A870-6419-4038-8606-C5E10CA7A172\",\"IS_URGENT\":\"1\",\"CNAME\":\"黄金\",\"ENAME\":\"gold\",\"ENAME_SUM\":1,\"CONTRACT_NO\":\"111\",\"INVOICE_NO\":\"111\",\"CUSTOM_ORDER_NO\":\"11\",\"COUNT\":111,\"WEIGHT\":111,\"VOLUME\":11,\"VOLUME_WEIGHT\":1837,\"CHARGING_WEIGHT\":1837,\"TOTAL_MONEY\":111,\"MONEY_TYPE\":\"300\",\"TRANSPORT_TYPE\":\"\",\"GOODS_ATTR\":\"101|109|107\",\"DATE_REQUIRED\":\"2019-03-27 10:59:00\",\"ORDER_GOODS_PACK\":[{\"ORDER_NO\":\"WXBA4AI2019000930\",\"COUNT\":111,\"PACKING_TYPE\":\"100\",\"X\":2,\"Y\":2,\"Z\":2,\"CAN_SUPER_POSITION\":\"200\",\"SUPER_POSITION_COUNT\":1,\"PIECE_WEIGHT\":9999999.999,\"IS_NON_STANDARD\":\"200\",\"IPPC\":\"1111111111111\"}],\"ORDER_CARGO_VALUE\":[{\"ORDER_NO\":\"WXBA4AI2019000930\",\"CARGO_VALUE\":111,\"currency\":\"300\"}]}],\"ORDER_LOADING_BILL\":[{\"ORDER_NO\":\"WXBA4AI2019000930\",\"BILL_TYPE\":\"01\",\"CHILD_BILL_TYPE\":\"0101\",\"BILL_NO\":\"111111111\",\"CHILD_BILL_NO\":\"111111\",\"TO_OFF_DATE\":\"2019-03-28 11:00:00\"}],\"ORDER_ICS_SERVICE\":[{\"ORDER_NO\":\"WXBA4AI2019000930\",\"PROPOSER\":\"D2C3A870-6419-4038-8606-C5E10CA7A172\",\"DEPARTMENT_ID\":\"6ABB73D7-7AB8-4B78-BE08-FFDF5C8DD5CF\",\"INSURANT\":\"58561940-E453-4BE6-A539-726647D8EAA5\",\"GOODS_NAME\":\"黄金\",\"CARGO_TYPE\":\"1\",\"PACKAGE_NUMBER\":111,\"PACKAGE_WEIGHT\":111,\"ACTUAL_CARRIER\":\"1\",\"BEAR_FEES\":\"第三方\",\"INTERNATIONAL_TRANSPORT_TYPE\":\"001\",\"INLAND_TRANSPORT_TYPE\":\"200002\",\"FROM_CITY\":\"无锡\",\"PICK_DATETIME\":\"2019-03-27 11:00:00\",\"TO_CITY\":\"想过\",\"FEE_COST_TYPE\":\"2\",\"SHIP_NAME\":\"cocos\",\"SAIL_NUM\":\"1111\",\"FLIGHT_NUM\":\"222\",\"TRANS_NO\":\"111\",\"CAR_NO\":\"1111111111\",\"Y_HT_GOODS\":\"0\",\"POLICY_REQUIREMENTS\":\"2\",\"ADDITIONAL_INSURANCE_TYPE\":\"1\",\"ADDITIONAL_INSURANCE_NAME\":\"\"}]}]}}";
        JSONObject jsonObject=getJSONObject1(json);
        //1 取出TOKEN token一样才能进行读取;
        //取出ICS
        Object ICS= getJSONObject1(jsonObject.toString()).get("ICS");
        //取出其中的DATA
        Object DATA= getJSONObject1(ICS.toString()).get("DATA");
        //转成数组
        JSONArray jsonArray=JSONArray.parseArray(DATA.toString());
        //取出保险订单选项
        Object ORDER_ICS_SERVICEArray=getJSONObject1(jsonArray.get(0).toString()).get("ORDER_ICS_SERVICE");
        //转成数组
        JSONArray jsonArray1=JSONArray.parseArray(ORDER_ICS_SERVICEArray.toString());

        Object ORDER_ICS_SERVICE=getJSONObject1(jsonArray1.get(0).toString());
       JSONObject ORDER_ICS_SERVICEJson=(JSONObject) ORDER_ICS_SERVICE;
        //获取其中的一个属性
        //取出货物价值属性
        //在ORDER_INFO==》ORDER_CARGO_VALUE
        //1取出 在ORDER_INFO==>现在是数组
        Object ORDER_INFOArray=getJSONObject1(jsonArray.get(0).toString()).get("ORDER_INFO");
        //转成数组
        JSONArray jsonArray2=JSONArray.parseArray(ORDER_INFOArray.toString());

        Object ORDER_INFO=getJSONObject1(jsonArray2.get(0).toString());

        JSONObject ORDER_INFOJson=(JSONObject) ORDER_INFO;
//        System.out.println(ORDER_INFOJson);

        Object GoodValuesArray=getJSONObject1(jsonArray2.get(0).toString()).get("ORDER_CARGO_VALUE");
        //转成数组
        JSONArray jsonArray3=JSONArray.parseArray(GoodValuesArray.toString());
        Object GoodValues=getJSONObject1(jsonArray3.get(0).toString());

        JSONObject GoodValuesJson=(JSONObject) GoodValues;

        System.out.println(GoodValuesJson);




        InsuranceOrder insuranceOrder=new InsuranceOrder();
        insuranceOrder.setId(ORDER_ICS_SERVICEJson.get("ORDER_NO").toString());//订单号
        insuranceOrder.setProposer(ORDER_ICS_SERVICEJson.get("PROPOSER").toString());//申请人
        DepartmentDataItem departmentDataItem=new DepartmentDataItem();
        departmentDataItem.setId(ORDER_ICS_SERVICEJson.get("DEPARTMENT_ID").toString());
        insuranceOrder.setDepartmentDataItem(departmentDataItem);
        insuranceOrder.setInsuredPersonName(ORDER_ICS_SERVICEJson.get("INSURANT").toString());
        insuranceOrder.setGoodsName(ORDER_ICS_SERVICEJson.get("GOODS_NAME").toString());
        insuranceOrder.setGoodsType(Integer.parseInt(ORDER_ICS_SERVICEJson.get("CARGO_TYPE").toString()));
        insuranceOrder.setPackageNumber(Integer.parseInt(ORDER_ICS_SERVICEJson.get("PACKAGE_NUMBER").toString()));
       //包装重量

        insuranceOrder.setPackageWeight(Float.parseFloat(ORDER_ICS_SERVICEJson.get("PACKAGE_WEIGHT").toString()));
        insuranceOrder.setActualCarrier(Integer.parseInt(ORDER_ICS_SERVICEJson.get("ACTUAL_CARRIER").toString()));
        insuranceOrder.setBearfees((ORDER_ICS_SERVICEJson.get("BEAR_FEES").toString()));

       //国际运输类型
        FreightDataItem internationalFreightDataItem=new FreightDataItem();
        internationalFreightDataItem.setId(ORDER_ICS_SERVICEJson.get("INTERNATIONAL_TRANSPORT_TYPE").toString());
        insuranceOrder.setInternationalFreightDataItem(internationalFreightDataItem);
        //国内运输类型
        FreightDataItem domesticFreightDataItem=new FreightDataItem();
        domesticFreightDataItem.setId(ORDER_ICS_SERVICEJson.get("INLAND_TRANSPORT_TYPE").toString());
        insuranceOrder.setDomesticFreightDataItem(domesticFreightDataItem);
         insuranceOrder.setOriginalPlace(ORDER_ICS_SERVICEJson.get("FROM_CITY").toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        insuranceOrder.setReceiveTime(sdf.parse(ORDER_ICS_SERVICEJson.get("PICK_DATETIME").toString()));
        insuranceOrder.setDestination(ORDER_ICS_SERVICEJson.get("TO_CITY").toString());
        insuranceOrder.setFeeCostType(Integer.parseInt(ORDER_ICS_SERVICEJson.get("FEE_COST_TYPE").toString()));
       //航名
        insuranceOrder.setFlightName(ORDER_ICS_SERVICEJson.get("SHIP_NAME").toString());
        //航次
        insuranceOrder.setFlightShift(ORDER_ICS_SERVICEJson.get("SAIL_NUM").toString());
        //航班号
        insuranceOrder.setFlightNumber(ORDER_ICS_SERVICEJson.get("FLIGHT_NUM").toString());
        //车次
        insuranceOrder.setTruckShift(ORDER_ICS_SERVICEJson.get("TRANS_NO").toString());
        //车牌号
        insuranceOrder.setLicensePlate(ORDER_ICS_SERVICEJson.get("CAR_NO").toString());
        //是否港澳
        if(Integer.parseInt(ORDER_ICS_SERVICEJson.get("Y_HT_GOODS").toString())==1){
            insuranceOrder.setyHTGoods(true);
        }
        if(Integer.parseInt(ORDER_ICS_SERVICEJson.get("Y_HT_GOODS").toString())==0){
            insuranceOrder.setyHTGoods(false);
        }
        insuranceOrder.setInsuranceOrderRequirement(Integer.parseInt(ORDER_ICS_SERVICEJson.get("POLICY_REQUIREMENTS").toString()));
        insuranceOrder.setFileInsurance(Integer.parseInt(ORDER_ICS_SERVICEJson.get("ADDITIONAL_INSURANCE_TYPE").toString()));
        insuranceOrder.setOtherInsurance(ORDER_ICS_SERVICEJson.get("ADDITIONAL_INSURANCE_NAME").toString());
       List<GoodsValue> goodsValueList=new ArrayList<>();
        System.out.println(insuranceOrder);




        for (int i=0;i<jsonArray3.size();i++){
            GoodsValue goodsValue=new GoodsValue();
            CurrencyDataItem currencyDataItem=new CurrencyDataItem();
            currencyDataItem.setCode(GoodValuesJson.get("currency").toString());
            goodsValue.setInsuranceOrderId(insuranceOrder.getId());
            goodsValue.setValue(Float.parseFloat(GoodValuesJson.get("CARGO_VALUE").toString()));
            goodsValueList.add(goodsValue);
            insuranceOrder.setGoodsValues(goodsValueList);
        }

        System.out.println(insuranceOrder);








    }

}
