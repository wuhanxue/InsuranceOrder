package com.jdlink.domain;

import com.jdlink.domain.dataItem.DepartmentDataItem;
import com.jdlink.domain.dataItem.FreightDataItem;
import com.jdlink.domain.dataItem.OrderStateDataItem;

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
     * 审批日期
     */
    private Date approvalDate;
    /**
     * 被投保人姓名
     */
    private String insuredPersonName;
    /**
     * 货物名称
     */
    private String goodsName;
    /**
     * 货物类别
     */
    private String goodsType;
    /**
     * 包装件数
     */
    private Integer packageNumber;
    /**
     * 包装重量
     */
    private Float packageWeight;
    /**
     * 实际承运人
     */
    private String actualCarrier;
    /**
     * 启运地
     */
    private String originalPlace;
    /**
     * 提货时间
     */
    private Date receiveTime;
    /**
     * 目的地
     */
    private String destination;
    /**
     * 航名
     */
    private String flightName;
    /**
     * 航班号
     */
    private String flightNumber;
    /**
     * 航次
     */
    private String flightShift;
    /**
     * 车次
     */
    private String truckShift;
    /**
     * 车牌号
     */
    private String licensePlate;
    /**
     * 货物价值
     */
    private Float goodsValue;
    /**
     * 是否粤港台货物
     */
    private Boolean gHTGoods;
    /**
     * 投保金额
     */
    private Float insuranceMoney;
    /**
     * 国际货运
     */
    private FreightDataItem internationalFreightDataItem;
    /**
     * 国内货运
     */
    private FreightDataItem domesticFreightDataItem;
    /**
     * 保单要求（正本1/复印件2/不需要3）
     */
    private Integer insuranceOrderRequirement;
    /**
     * 附件险（吊装险1/其它2）
     */
    private Integer fileInsurance;
    /**
     * 其他险种说明
     */
    private String ortherInsurance;
    /**
     * 制单客服
     */
    private String orderMakedService;
    /**
     * 创建时间
     */
    private Date creationTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 修改人
     */
    private String modifier;

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderStateDataItem getOrderStateDataItem() {
        return orderStateDataItem;
    }

    public void setOrderStateDataItem(OrderStateDataItem orderStateDataItem) {
        this.orderStateDataItem = orderStateDataItem;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public DepartmentDataItem getDepartmentDataItem() {
        return departmentDataItem;
    }

    public void setDepartmentDataItem(DepartmentDataItem departmentDataItem) {
        this.departmentDataItem = departmentDataItem;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getInsuredPersonName() {
        return insuredPersonName;
    }

    public void setInsuredPersonName(String insuredPersonName) {
        this.insuredPersonName = insuredPersonName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(Integer packageNumber) {
        this.packageNumber = packageNumber;
    }

    public Float getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(Float packageWeight) {
        this.packageWeight = packageWeight;
    }

    public String getActualCarrier() {
        return actualCarrier;
    }

    public void setActualCarrier(String actualCarrier) {
        this.actualCarrier = actualCarrier;
    }

    public String getOriginalPlace() {
        return originalPlace;
    }

    public void setOriginalPlace(String originalPlace) {
        this.originalPlace = originalPlace;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightShift() {
        return flightShift;
    }

    public void setFlightShift(String flightShift) {
        this.flightShift = flightShift;
    }

    public String getTruckShift() {
        return truckShift;
    }

    public void setTruckShift(String truckShift) {
        this.truckShift = truckShift;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Float getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(Float goodsValue) {
        this.goodsValue = goodsValue;
    }

    public Boolean getgHTGoods() {
        return gHTGoods;
    }

    public void setgHTGoods(Boolean gHTGoods) {
        this.gHTGoods = gHTGoods;
    }

    public Float getInsuranceMoney() {
        return insuranceMoney;
    }

    public void setInsuranceMoney(Float insuranceMoney) {
        this.insuranceMoney = insuranceMoney;
    }

    public FreightDataItem getInternationalFreightDataItem() {
        return internationalFreightDataItem;
    }

    public void setInternationalFreightDataItem(FreightDataItem internationalFreightDataItem) {
        this.internationalFreightDataItem = internationalFreightDataItem;
    }

    public FreightDataItem getDomesticFreightDataItem() {
        return domesticFreightDataItem;
    }

    public void setDomesticFreightDataItem(FreightDataItem domesticFreightDataItem) {
        this.domesticFreightDataItem = domesticFreightDataItem;
    }

    public Integer getInsuranceOrderRequirement() {
        return insuranceOrderRequirement;
    }

    public void setInsuranceOrderRequirement(Integer insuranceOrderRequirement) {
        this.insuranceOrderRequirement = insuranceOrderRequirement;
    }

    public Integer getFileInsurance() {
        return fileInsurance;
    }

    public void setFileInsurance(Integer fileInsurance) {
        this.fileInsurance = fileInsurance;
    }

    public String getOrtherInsurance() {
        return ortherInsurance;
    }

    public void setOrtherInsurance(String ortherInsurance) {
        this.ortherInsurance = ortherInsurance;
    }

    public String getOrderMakedService() {
        return orderMakedService;
    }

    public void setOrderMakedService(String orderMakedService) {
        this.orderMakedService = orderMakedService;
    }

    @Override
    public String toString() {
        return "InsuranceOrder{" +
                "id='" + id + '\'' +
                ", orderStateDataItem=" + orderStateDataItem +
                ", proposer='" + proposer + '\'' +
                ", departmentDataItem=" + departmentDataItem +
                ", approvalDate=" + approvalDate +
                ", insuredPersonName='" + insuredPersonName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", packageNumber=" + packageNumber +
                ", packageWeight=" + packageWeight +
                ", actualCarrier='" + actualCarrier + '\'' +
                ", originalPlace='" + originalPlace + '\'' +
                ", receiveTime=" + receiveTime +
                ", destination='" + destination + '\'' +
                ", flightName='" + flightName + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", flightShift='" + flightShift + '\'' +
                ", truckShift='" + truckShift + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", goodsValue=" + goodsValue +
                ", gHTGoods=" + gHTGoods +
                ", insuranceMoney=" + insuranceMoney +
                ", internationalFreightDataItem=" + internationalFreightDataItem +
                ", domesticFreightDataItem=" + domesticFreightDataItem +
                ", insuranceOrderRequirement=" + insuranceOrderRequirement +
                ", fileInsurance=" + fileInsurance +
                ", ortherInsurance='" + ortherInsurance + '\'' +
                ", orderMakedService='" + orderMakedService + '\'' +
                '}';
    }
}
