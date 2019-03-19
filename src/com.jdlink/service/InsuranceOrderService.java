package com.jdlink.service;

import com.jdlink.domain.GoodsValue;
import com.jdlink.domain.InsuranceOrder;
import com.jdlink.domain.InsuranceOrderItem;
import com.jdlink.domain.Page;

import java.util.List;

public interface InsuranceOrderService {

    /*查询所有订单*/
    List<InsuranceOrder> listInsuranceOrder(Page page);

    /*通过单号查找订单*/
    InsuranceOrder getInsuranceOrderById(String id);

    /*获取订单总数*/
    int getTotalInsuranceOrder();

    /*订单查询*/
    List<InsuranceOrder>searchInsuranceOrder(InsuranceOrder insuranceOrder);

    /*订单查询总数*/
    int searchInsuranceOrderTotal(InsuranceOrder insuranceOrder);

    /*根据保险单号查看明细(保单)*/
    List<InsuranceOrderItem> getInsuranceOrderItemById(String id);

    /*更新保单信息*/
    void updateInsuranceOrderItem(InsuranceOrderItem insuranceOrderItem);

    /*删除保单信息*/
    void deleteInsuranceOrderItemById(String id);

    /*添加保单信息*/
    void addInsuranceOrderItem(InsuranceOrderItem insuranceOrderItem);

    /*设置保单的路径名*/
    void setInsurancePolicyFileUrl(InsuranceOrderItem insuranceOrderItem);

    /*接单*/
    void receiptById(String id);

    /*作废*/
    void cancelById(String id);

    /*投保*/
    void insured(String id);

    /*关闭*/
    void shutDownById(String id);

    /*生成异常单*/
    void getAbnormal(InsuranceOrderItem insuranceOrderItem);

    /*根据订单号查询货物价值信息*/
    List<GoodsValue> getGoodsValueById(String id);

    /*根据保单号查询保单信息*/
    InsuranceOrderItem getInsuranceOrderItemByItemId(String id);

    /*获取所有的保单*/
    List<InsuranceOrderItem>listInsuranceOrderItem();

    /*关闭保单*/
    void shutInsuranceOrderItemDownById(String id);

    void addInsuranceOrder(InsuranceOrder insuranceOrder);

    void addGoodsValue(GoodsValue goodsValue);
}
