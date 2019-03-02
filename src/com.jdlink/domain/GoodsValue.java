package com.jdlink.domain;

import com.jdlink.domain.dataItem.CurrencyDataItem;

public class GoodsValue {
    /**
     * id
     */
    private int id;
    /**
     * 订单编号
     */
    private String insuranceOrderId;
    /**
     * 货物价值
     */
    private Float value;
    /**
     * 币种（币制）
     */


    private CurrencyDataItem currencyDataItem;
    /*
     * 保单数据结构
     * */
    private InsuranceOrderItem insuranceOrderItem;

    public InsuranceOrderItem getInsuranceOrderItem() {
        return insuranceOrderItem;
    }

    public void setInsuranceOrderItem(InsuranceOrderItem insuranceOrderItem) {
        this.insuranceOrderItem = insuranceOrderItem;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInsuranceOrderId() {
        return insuranceOrderId;
    }

    public void setInsuranceOrderId(String insuranceOrderId) {
        this.insuranceOrderId = insuranceOrderId;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public CurrencyDataItem getCurrencyDataItem() {
        return currencyDataItem;
    }

    public void setCurrencyDataItem(CurrencyDataItem currencyDataItem) {
        this.currencyDataItem = currencyDataItem;
    }

    @Override
    public String toString() {
        return "GoodsValue{" +
                "id=" + id +
                ", insuranceOrderId='" + insuranceOrderId + '\'' +
                ", value=" + value +
                ", currencyDataItem=" + currencyDataItem +
                '}';
    }
}
