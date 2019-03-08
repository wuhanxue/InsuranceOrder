package com.jdlink.domain;

public class InsuranceDetail {

    /*订单号*/
    private String ORDER_NO;
    /*保单号*/
    private String POLICY_NO;
    /*保险公司*/
    private String INSURANCE_COMPANY;
    /*保费*/
    private String FEE;
    /*投保日期*/
    private String INSURANCE_DATE;

    /*单据状态*/
    private String DATASTATUS;
    /*备用字段1*/
    private String DEF1="";
    /*备用字段2*/
    private String DEF2="";
    /*备用字段3*/
    private String DEF3="";
    /*备用字段4*/
    private String DEF4="";
    /*备用字段5*/
    private String DEF5="";


    public String getDATASTATUS() {
        return DATASTATUS;
    }

    public void setDATASTATUS(String DATASTATUS) {
        this.DATASTATUS = DATASTATUS;
    }

    public String getORDER_NO() {
        return ORDER_NO;
    }

    public void setORDER_NO(String ORDER_NO) {
        this.ORDER_NO = ORDER_NO;
    }

    public String getPOLICY_NO() {
        return POLICY_NO;
    }

    public void setPOLICY_NO(String POLICY_NO) {
        this.POLICY_NO = POLICY_NO;
    }

    public String getINSURANCE_COMPANY() {
        return INSURANCE_COMPANY;
    }

    public void setINSURANCE_COMPANY(String INSURANCE_COMPANY) {
        this.INSURANCE_COMPANY = INSURANCE_COMPANY;
    }

    public String getFEE() {
        return FEE;
    }

    public void setFEE(String FEE) {
        this.FEE = FEE;
    }

    public String getINSURANCE_DATE() {
        return INSURANCE_DATE;
    }

    public void setINSURANCE_DATE(String INSURANCE_DATE) {
        this.INSURANCE_DATE = INSURANCE_DATE;
    }

    public String getDEF1() {
        return DEF1;
    }

    public void setDEF1(String DEF1) {
        this.DEF1 = DEF1;
    }

    public String getDEF2() {
        return DEF2;
    }

    public void setDEF2(String DEF2) {
        this.DEF2 = DEF2;
    }

    public String getDEF3() {
        return DEF3;
    }

    public void setDEF3(String DEF3) {
        this.DEF3 = DEF3;
    }

    public String getDEF4() {
        return DEF4;
    }

    public void setDEF4(String DEF4) {
        this.DEF4 = DEF4;
    }

    public String getDEF5() {
        return DEF5;
    }

    public void setDEF5(String DEF5) {
        this.DEF5 = DEF5;
    }

    @Override
    public String toString() {
        return "InsuranceDetail{" +
                "ORDER_NO='" + ORDER_NO + '\'' +
                ", POLICY_NO='" + POLICY_NO + '\'' +
                ", INSURANCE_COMPANY='" + INSURANCE_COMPANY + '\'' +
                ", FEE='" + FEE + '\'' +
                ", INSURANCE_DATE='" + INSURANCE_DATE + '\'' +
                ", DEF1='" + DEF1 + '\'' +
                ", DEF2='" + DEF2 + '\'' +
                ", DEF3='" + DEF3 + '\'' +
                ", DEF4='" + DEF4 + '\'' +
                ", DEF5='" + DEF5 + '\'' +
                '}';
    }
}
