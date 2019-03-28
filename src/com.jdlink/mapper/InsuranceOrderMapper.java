package com.jdlink.mapper;

import com.jdlink.domain.GoodsValue;
import com.jdlink.domain.InsuranceOrder;
import com.jdlink.domain.InsuranceOrderItem;
import com.jdlink.domain.Page;

import java.util.List;

public interface InsuranceOrderMapper {

    List<InsuranceOrder>listInsuranceOrder(Page page);

    InsuranceOrder getInsuranceOrderById(String id);

    int getTotalInsuranceOrder();

    List<InsuranceOrder>searchInsuranceOrder(InsuranceOrder insuranceOrder);

    int searchInsuranceOrderTotal(InsuranceOrder insuranceOrder);

    List<InsuranceOrderItem> getInsuranceOrderItemById(String id);

    void updateInsuranceOrderItem(InsuranceOrderItem insuranceOrderItem);

    void deleteInsuranceOrderItemById(String id);

    void addInsuranceOrderItem(InsuranceOrderItem insuranceOrderItem);

    void setInsurancePolicyFileUrl(InsuranceOrderItem insuranceOrderItem);

    void receiptById(String id);

    void cancelById(String id);

    void insured(String id);

    void shutDownById(String id);

    void getAbnormal(InsuranceOrderItem insuranceOrderItem);

    List<GoodsValue> getGoodsValueById(String id);

    InsuranceOrderItem getInsuranceOrderItemByItemId(String id);

    List<InsuranceOrderItem>listInsuranceOrderItem();

    void shutInsuranceOrderItemDownById(String id);

    void addInsuranceOrder(InsuranceOrder insuranceOrder);

    void addGoodsValue(GoodsValue goodsValue);

    void updateInsuranceOrder(InsuranceOrder insuranceOrder);

    void deleteGoodValues(String insuranceOrderId);
}
