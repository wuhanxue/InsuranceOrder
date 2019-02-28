package com.jdlink.domain;
/*传给云平台的数据*/
public class TRACKING {

    /*简单字符串*/
    private String ROWID;

    /*订单编号*/
    private String ORDERNO;

    /*子服务类型*/
    private String SUBSERVICECODE="";

    /*负责人*/
    private String MANAGER;

    /*实际完成时间*/
    private String ATC;

    /*作业内容*/
    private String OPERATIONCONTENT;

    /*操作部门*/
   private String OPERATIONDEPARTMENT="保险";

   /*节点类型 状态*/
    private String NODETYPE;

    /*服务类型*/
    private String SERVICETYPE="ICS";

    /*报险单号*/
    private String ENTRUSTORDERNO;

    /*操作人*/
    private String OPERATOR;

    public String getROWID() {
        return ROWID;
    }

    public void setROWID(String ROWID) {
        this.ROWID = ROWID;
    }

    public String getORDERNO() {
        return ORDERNO;
    }

    public void setORDERNO(String ORDERNO) {
        this.ORDERNO = ORDERNO;
    }

    public String getSUBSERVICECODE() {
        return SUBSERVICECODE;
    }

    public void setSUBSERVICECODE(String SUBSERVICECODE) {
        this.SUBSERVICECODE = SUBSERVICECODE;
    }

    public String getMANAGER() {
        return MANAGER;
    }

    public void setMANAGER(String MANAGER) {
        this.MANAGER = MANAGER;
    }

    public String getATC() {
        return ATC;
    }

    public void setATC(String ATC) {
        this.ATC = ATC;
    }

    public String getOPERATIONCONTENT() {
        return OPERATIONCONTENT;
    }

    public void setOPERATIONCONTENT(String OPERATIONCONTENT) {
        this.OPERATIONCONTENT = OPERATIONCONTENT;
    }

    public String getOPERATIONDEPARTMENT() {
        return OPERATIONDEPARTMENT;
    }

    public void setOPERATIONDEPARTMENT(String OPERATIONDEPARTMENT) {
        this.OPERATIONDEPARTMENT = OPERATIONDEPARTMENT;
    }

    public String getNODETYPE() {
        return NODETYPE;
    }

    public void setNODETYPE(String NODETYPE) {
        this.NODETYPE = NODETYPE;
    }

    public String getSERVICETYPE() {
        return SERVICETYPE;
    }

    public void setSERVICETYPE(String SERVICETYPE) {
        this.SERVICETYPE = SERVICETYPE;
    }

    public String getENTRUSTORDERNO() {
        return ENTRUSTORDERNO;
    }

    public void setENTRUSTORDERNO(String ENTRUSTORDERNO) {
        this.ENTRUSTORDERNO = ENTRUSTORDERNO;
    }

    public String getOPERATOR() {
        return OPERATOR;
    }

    public void setOPERATOR(String OPERATOR) {
        this.OPERATOR = OPERATOR;
    }

    @Override
    public String toString() {
        return "TRACKING{" +
                "ROWID='" + ROWID + '\'' +
                ", ORDERNO='" + ORDERNO + '\'' +
                ", SUBSERVICECODE='" + SUBSERVICECODE + '\'' +
                ", MANAGER='" + MANAGER + '\'' +
                ", ATC='" + ATC + '\'' +
                ", OPERATIONCONTENT='" + OPERATIONCONTENT + '\'' +
                ", OPERATIONDEPARTMENT='" + OPERATIONDEPARTMENT + '\'' +
                ", NODETYPE='" + NODETYPE + '\'' +
                ", SERVICETYPE='" + SERVICETYPE + '\'' +
                ", ENTRUSTORDERNO='" + ENTRUSTORDERNO + '\'' +
                ", OPERATOR='" + OPERATOR + '\'' +
                '}';
    }
}

