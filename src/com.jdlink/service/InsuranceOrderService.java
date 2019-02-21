package com.jdlink.service;

import com.jdlink.domain.InsuranceOrder;

import java.util.List;

public interface InsuranceOrderService {

    /*查询所有订单*/
    List<InsuranceOrder> listInsuranceOrder();
}
