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
        String json="{\"TOKEN\":\"a91ddbde559f47249c047bd3afdf8613\",\"ICS\":{\"MESSAGE_ID\":\"WXBA4AI2019000911\",\"MESSAGE_FORMAT\":\"json\",\"MESSAGE_TYPE\":\"ICS_ORDERS\",\"COMPRRESS\":\"0\",\"SEND_DATE\":\"2019-03-19 10:20:06\",\"SEND_SYS\":\"OMS\",\"SENDER\":\"0001\",\"RECEIVE_SYS\":\"ICS\",\"RECEIVER\":\"00001\",\"REMARK\":\"\",\"DATA\":[{\"ORDER_INFO\":{\"ORDER_NO\":\"WXBA4AI2019000911\",\"TRUST_CUSTOMER_ID\":\"C83DB74F-4BC7-4E6C-B11B-B8A049B90796\",\"DELIVERY_CUSTOMER_ID\":\"58561940-E453-4BE6-A539-726647D8EAA5\",\"RECEIVING_CUSTOMER_ID\":\"58561940-E453-4BE6-A539-726647D8EAA5\",\"PAYMENT_SOLUTION\":\"CQS1809280001\",\"FREIGHT_FLOW\":\"001\",\"SERVICE_TYPE\":\"500\",\"PM_TEAM_ID\":\"6ABB73D7-7AB8-4B78-BE08-FFDF5C8DD5CF\",\"CUSTOMER_BU\":\"\",\"ORDER_RECIEVER\":\"D2C3A870-6419-4038-8606-C5E10CA7A172\",\"ORDER_MAKER\":\"D2C3A870-6419-4038-8606-C5E10CA7A172\",\"ORDER_OPERATOR\":\"D2C3A870-6419-4038-8606-C5E10CA7A172\",\"IS_URGENT\":\"1\",\"CNAME\":\"黄金\",\"ENAME\":\"gold\",\"ENAME_SUM\":1,\"CONTRACT_NO\":\"1111\",\"INVOICE_NO\":\"22222\",\"CUSTOM_ORDER_NO\":\"33333\",\"COUNT\":11,\"WEIGHT\":11,\"VOLUME\":111,\"VOLUME_WEIGHT\":18537,\"CHARGING_WEIGHT\":18537,\"TOTAL_MONEY\":1111,\"MONEY_TYPE\":\"502\",\"TRANSPORT_TYPE\":\"\",\"GOODS_ATTR\":\"101|105|111|108|103|102|106|104|109|107|110\",\"DATE_REQUIRED\":\"2019-03-19 10:11:00\",\"ORDER_GOODS_PACK\":[{\"ORDER_NO\":\"WXBA4AI2019000911\",\"COUNT\":11,\"PACKING_TYPE\":\"100\",\"X\":2,\"Y\":2,\"Z\":2,\"CAN_SUPER_POSITION\":\"200\",\"SUPER_POSITION_COUNT\":1,\"PIECE_WEIGHT\":1,\"IS_NON_STANDARD\":\"200\",\"IPPC\":\"1111\"}],\"ORDER_CARGO_VALUE\":[{\"ORDER_NO\":\"WXBA4AI2019000911\",\"CARGO_VALUE\":1111,\"currency\":\"01\"}]},\"ORDER_LOADING_BILL\":[{\"ORDER_NO\":\"WXBA4AI2019000911\",\"BILL_TYPE\":\"01\",\"CHILD_BILL_TYPE\":\"0101\",\"BILL_NO\":\"123\",\"CHILD_BILL_NO\":\"456\",\"TO_OFF_DATE\":\"2019-03-19 10:11:00\"}],\"ORDER_ICS_SERVICE\":{\"ORDER_NO\":\"WXBA4AI2019000911\",\"PROPOSER\":\"D2C3A870-6419-4038-8606-C5E10CA7A172\",\"DEPARTMENT_ID\":\"6ABB73D7-7AB8-4B78-BE08-FFDF5C8DD5CF\",\"INSURANT\":\"58561940-E453-4BE6-A539-726647D8EAA5\",\"GOODS_NAME\":\"黄金\",\"CARGO_TYPE\":\"0\",\"PACKAGE_NUMBER\":11,\"PACKAGE_WEIGHT\":11,\"ACTUAL_CARRIER\":\"我司|供方|第三方\",\"BEAR_FEES\":\"1\",\"INTERNATIONAL_TRANSPORT_TYPE\":\"001\",\"INLAND_TRANSPORT_TYPE\":\"200002\",\"FROM_CITY\":\"无锡\",\"PICK_DATETIME\":\"2019-03-19 10:12:00\",\"TO_CITY\":\"香港\",\"FEE_COST_TYPE\":\"0\",\"SHIP_NAME\":\"MH\",\"SAIL_NUM\":\"MH730\",\"FLIGHT_NUM\":\"730\",\"TRANS_NO\":\"kk123\",\"CAR_NO\":\"123\",\"Y_HT_GOODS\":\"1\",\"POLICY_REQUIREMENTS\":\"0\",\"ADDITIONAL_INSURANCE_TYPE\":\"0\",\"ADDITIONAL_INSURANCE_NAME\":\"\"}}]}}";
        JSONObject jsonObject=getJSONObject1(json);
        //1 取出TOKEN token一样才能进行读取;
        //取出ICS
        Object ICS= getJSONObject1(jsonObject.toString()).get("ICS");
        //取出其中的DATA
        Object DATA= getJSONObject1(ICS.toString()).get("DATA");
        //转成数组
        JSONArray jsonArray=JSONArray.parseArray(DATA.toString());
        //取出保险订单选项
        Object ORDER_ICS_SERVICE=getJSONObject1(jsonArray.get(0).toString()).get("ORDER_ICS_SERVICE");
        System.out.println(ORDER_ICS_SERVICE);
        //获取其中的一个属性
        //取出货物价值属性
        //在ORDER_INFO==》ORDER_CARGO_VALUE
        //1取出 在ORDER_INFO
        Object ORDER_INFO=getJSONObject1(jsonArray.get(0).toString()).get("ORDER_INFO");

        InsuranceOrder insuranceOrder=new InsuranceOrder();
        insuranceOrder.setId(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("ORDER_NO").toString());//订单号
        insuranceOrder.setProposer(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("PROPOSER").toString());//申请人
        DepartmentDataItem departmentDataItem=new DepartmentDataItem();
        departmentDataItem.setId(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("DEPARTMENT_ID").toString());
        insuranceOrder.setDepartmentDataItem(departmentDataItem);
        insuranceOrder.setInsuredPersonName(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("INSURANT").toString());
        insuranceOrder.setGoodsName(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("GOODS_NAME").toString());
        insuranceOrder.setGoodsType(Integer.parseInt(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("CARGO_TYPE").toString()));
        insuranceOrder.setPackageNumber(Integer.parseInt(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("PACKAGE_NUMBER").toString()));
       //包装重量
//        System.out.println(getJSONObject(ORDER_ICS_SERVICE.toString()).get("PACKAGE_NUMBER"));
//        System.out.println(getJSONObject(ORDER_ICS_SERVICE.toString()).get("PACKAGE_WEIGHT"));
        insuranceOrder.setPackageWeight(Float.parseFloat(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("PACKAGE_WEIGHT").toString()));
        insuranceOrder.setActualCarrier(Integer.parseInt(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("BEAR_FEES").toString()));
       //国际运输类型
        FreightDataItem internationalFreightDataItem=new FreightDataItem();
        internationalFreightDataItem.setId(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("INTERNATIONAL_TRANSPORT_TYPE").toString());
        insuranceOrder.setInternationalFreightDataItem(internationalFreightDataItem);
        //国内运输类型
        FreightDataItem domesticFreightDataItem=new FreightDataItem();
        domesticFreightDataItem.setId(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("INLAND_TRANSPORT_TYPE").toString());
        insuranceOrder.setDomesticFreightDataItem(domesticFreightDataItem);
         insuranceOrder.setOriginalPlace(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("FROM_CITY").toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        insuranceOrder.setReceiveTime(sdf.parse(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("PICK_DATETIME").toString()));
        insuranceOrder.setDestination(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("TO_CITY").toString());
        insuranceOrder.setFeeCostType(Integer.parseInt(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("FEE_COST_TYPE").toString()));
       //航名
        insuranceOrder.setFlightName(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("SHIP_NAME").toString());
        //航次
        insuranceOrder.setFlightShift(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("SAIL_NUM").toString());
        //航班号
        insuranceOrder.setFlightNumber(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("FLIGHT_NUM").toString());
        //车次
        insuranceOrder.setTruckShift(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("TRANS_NO").toString());
        //车牌号
        insuranceOrder.setLicensePlate(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("CAR_NO").toString());
        //是否港澳
        if(Integer.parseInt(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("Y_HT_GOODS").toString())==1){
            insuranceOrder.setyHTGoods(true);
        }
        if(Integer.parseInt(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("Y_HT_GOODS").toString())==0){
            insuranceOrder.setyHTGoods(false);
        }
        insuranceOrder.setInsuranceOrderRequirement(Integer.parseInt(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("POLICY_REQUIREMENTS").toString()));
        insuranceOrder.setFileInsurance(Integer.parseInt(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("ADDITIONAL_INSURANCE_TYPE").toString()));
        insuranceOrder.setOtherInsurance(getJSONObject1(ORDER_ICS_SERVICE.toString()).get("ADDITIONAL_INSURANCE_NAME").toString());
       List<GoodsValue> goodsValueList=new ArrayList<>();
        System.out.println(insuranceOrder);
        Object ORDER_CARGO_VALUE=getJSONObject1(ORDER_INFO.toString()).get("ORDER_CARGO_VALUE");

        JSONArray ORDER_CARGO_VALUEArray=JSONArray.parseArray(ORDER_CARGO_VALUE.toString());
        for (int i=0;i<ORDER_CARGO_VALUEArray.size();i++){
            GoodsValue goodsValue=new GoodsValue();
            CurrencyDataItem currencyDataItem=new CurrencyDataItem();
            currencyDataItem.setCode(getJSONObject1(ORDER_CARGO_VALUEArray.get(i).toString()).get("currency").toString());
            goodsValue.setInsuranceOrderId(insuranceOrder.getId());
            goodsValue.setValue(Float.parseFloat(getJSONObject1(ORDER_CARGO_VALUEArray.get(i).toString()).get("CARGO_VALUE").toString()));
            goodsValueList.add(goodsValue);
            insuranceOrder.setGoodsValues(goodsValueList);
        }

        System.out.println(insuranceOrder);








    }

}
