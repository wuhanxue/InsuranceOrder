package com.jdlink.mapper;

import com.jdlink.domain.InsuranceOrder;

import java.util.List;

public interface InsuranceOrderMapper {

    List<InsuranceOrder>listInsuranceOrder();

    InsuranceOrder getInsuranceOrderById(String id);
}
