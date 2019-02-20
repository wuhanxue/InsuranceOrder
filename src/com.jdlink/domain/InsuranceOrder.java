package com.jdlink.domain;

import java.util.Date;

/**
 * 订单数据结构
 */
public class InsuranceOrder {
    /**
     * 订单编号
     */
    private String id;
    /**
     * 订单状态数据结构
     */
    private OrderStateDataItem orderStateDataItem;
    /**
     * 申请人
     */
    private String proposer;
    /**
     * 申请部门数据字典
     */
    private DepartmentDataItem departmentDataItem;
    /**
     * 投保日期
     */
    private Date insureDate;
    /**
     * 审批日期
     */
    private Date approvalDate;
    /**
     * 被投保人姓名
     */
    private String insuredPersonName;
    /**
     * 货物价值
     */
    private float goodsValue;
    /**
     * 保险公司名称
     */
    private String insureCompanyName;
    /**
     * 保险费
     */
    private float premium;
    /**
     * 制单客服
     */
    private String orderMakedService;


}
