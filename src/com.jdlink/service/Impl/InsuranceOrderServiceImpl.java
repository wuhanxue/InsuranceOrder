package com.jdlink.service.Impl;

import com.jdlink.domain.InsuranceOrder;
import com.jdlink.mapper.InsuranceOrderMapper;
import com.jdlink.service.InsuranceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceOrderServiceImpl implements InsuranceOrderService {
    @Autowired
    InsuranceOrderMapper insuranceOrderMapper;

    @Override
    public List<InsuranceOrder> listInsuranceOrder() {
        return insuranceOrderMapper.listInsuranceOrder();
    }

    @Override
    public InsuranceOrder getInsuranceOrderById(String id) {
        return insuranceOrderMapper.getInsuranceOrderById(id);
    }
}
