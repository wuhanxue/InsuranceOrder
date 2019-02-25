package com.jdlink.mapper;

import com.jdlink.domain.InsuranceOrder;
import com.jdlink.domain.Page;

import java.util.List;

public interface InsuranceOrderMapper {

    List<InsuranceOrder>listInsuranceOrder(Page page);

    InsuranceOrder getInsuranceOrderById(String id);

    int getTotalInsuranceOrder();

    List<InsuranceOrder>searchInsuranceOrder(InsuranceOrder insuranceOrder);

    int searchInsuranceOrderTotal(InsuranceOrder insuranceOrder);
}
