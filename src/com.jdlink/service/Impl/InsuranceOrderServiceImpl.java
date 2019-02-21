package com.jdlink.service.Impl;

import com.jdlink.mapper.InsuranceOrderMapper;
import com.jdlink.service.InsuranceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceOrderServiceImpl implements InsuranceOrderService {
    @Autowired
    InsuranceOrderMapper insuranceOrderMapper;
}
