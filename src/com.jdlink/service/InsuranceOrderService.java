package com.jdlink.service;

import com.jdlink.domain.InsuranceOrder;
import com.jdlink.domain.Page;

import java.util.List;

public interface InsuranceOrderService {

    /*查询所有订单*/
    List<InsuranceOrder> listInsuranceOrder(Page page);

    /*通过单号查找订单*/
    InsuranceOrder getInsuranceOrderById(String id);

    /*获取订单总数*/
    int getTotalInsuranceOrder();
}
