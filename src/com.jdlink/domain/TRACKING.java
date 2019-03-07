package com.jdlink.domain;
/*传给云平台的数据*/
public class TRACKING {

    /*简单字符串*/
    private String rowId;

    /*订单编号*/
    private String orderNo;

    /*子服务类型*/
    private String subServiceCode="";

    /*负责人*/
    private String manager;

    /*实际完成时间*/
    private String atc;

    /*作业内容*/
    private String operationContent;

    /*操作部门*/
   private String operationDepartment="保险";

   /*节点类型 状态*/
    private String nodeType;

//    /*服务类型*/
//    private String SERVICETYPE="ICS";

    /*服务类型*/
    private String serviceType;

    /*操作人*/
    private String operator;

    /*保单号*/
    private String entrustOrderNo;

    public String getEntrustOrderNo() {
        return entrustOrderNo;
    }

    public void setEntrustOrderNo(String entrustOrderNo) {
        this.entrustOrderNo = entrustOrderNo;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSubServiceCode() {
        return subServiceCode;
    }

    public void setSubServiceCode(String subServiceCode) {
        this.subServiceCode = subServiceCode;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAtc() {
        return atc;
    }

    public void setAtc(String atc) {
        this.atc = atc;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public String getOperationDepartment() {
        return operationDepartment;
    }

    public void setOperationDepartment(String operationDepartment) {
        this.operationDepartment = operationDepartment;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

//    public String getSERVICETYPE() {
//        return SERVICETYPE;
//    }
//
//    public void setSERVICETYPE(String SERVICETYPE) {
//        this.SERVICETYPE = SERVICETYPE;
//    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "TRACKING{" +
                "rowId='" + rowId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", subServiceCode='" + subServiceCode + '\'' +
                ", manager='" + manager + '\'' +
                ", atc='" + atc + '\'' +
                ", operationContent='" + operationContent + '\'' +
                ", operationDepartment='" + operationDepartment + '\'' +
                ", nodeType='" + nodeType + '\'' +
//                ", SERVICETYPE='" + SERVICETYPE + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}

