package com.jdlink.controller;

import com.jdlink.service.InsuranceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InsuranceOrder {
@Autowired
    InsuranceOrderService insuranceOrderService;
}
