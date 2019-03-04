package com.jdlink.service.Impl;

import com.jdlink.domain.GoodsValue;
import com.jdlink.domain.InsuranceOrder;
import com.jdlink.domain.InsuranceOrderItem;
import com.jdlink.domain.Page;
import com.jdlink.mapper.InsuranceOrderMapper;
import com.jdlink.service.InsuranceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceOrderServiceImpl implements InsuranceOrderService


{
    @Autowired
    InsuranceOrderMapper insuranceOrderMapper;

    @Override
    public List<InsuranceOrder> listInsuranceOrder(Page page) {
        return insuranceOrderMapper.listInsuranceOrder(page);
    }

    @Override
    public InsuranceOrder getInsuranceOrderById(String id) {
        return insuranceOrderMapper.getInsuranceOrderById(id);
    }

    @Override
    public int getTotalInsuranceOrder() {
        return insuranceOrderMapper.getTotalInsuranceOrder();
    }

    @Override
    public List<InsuranceOrder> searchInsuranceOrder(InsuranceOrder insuranceOrder) {
        return insuranceOrderMapper.searchInsuranceOrder(insuranceOrder);
    }

    @Override
    public int searchInsuranceOrderTotal(InsuranceOrder insuranceOrder) {
        return insuranceOrderMapper.searchInsuranceOrderTotal(insuranceOrder);
    }

    @Override
    public List<InsuranceOrderItem> getInsuranceOrderItemById(String id) {
        return insuranceOrderMapper.getInsuranceOrderItemById(id);
    }

    @Override
    public void updateInsuranceOrderItem(InsuranceOrderItem insuranceOrderItem) {
        insuranceOrderMapper.updateInsuranceOrderItem(insuranceOrderItem);
    }

    @Override
    public void deleteInsuranceOrderItemById(String id) {
        insuranceOrderMapper.deleteInsuranceOrderItemById(id);
    }

    @Override
    public void addInsuranceOrderItem(InsuranceOrderItem insuranceOrderItem) {
        insuranceOrderMapper.addInsuranceOrderItem(insuranceOrderItem);
    }

    @Override
    public void setInsurancePolicyFileUrl(InsuranceOrderItem insuranceOrderItem) {
        insuranceOrderMapper.setInsurancePolicyFileUrl(insuranceOrderItem);
    }

    @Override
    public void receiptById(String id) {
        insuranceOrderMapper.receiptById(id);
    }

    @Override
    public void cancelById(String id) {
        insuranceOrderMapper.cancelById(id);
    }

    @Override
    public void insured(String id) {
        insuranceOrderMapper.insured(id);
    }

    @Override
    public void shutDownById(String id) {
        insuranceOrderMapper.shutDownById(id);
    }

    @Override
    public void getAbnormal(InsuranceOrderItem insuranceOrderItem) {
        insuranceOrderMapper.getAbnormal(insuranceOrderItem);
    }

    @Override
    public List<GoodsValue> getGoodsValueById(String id) {
        return insuranceOrderMapper.getGoodsValueById(id);
    }

    @Override
    public InsuranceOrderItem getInsuranceOrderItemByItemId(String id) {
        return insuranceOrderMapper.getInsuranceOrderItemByItemId(id);
    }


}
