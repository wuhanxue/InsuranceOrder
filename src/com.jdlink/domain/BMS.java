package com.jdlink.domain;
/*结算反馈接口具体的数据*/
public class BMS {

    /*单据类型*/
    private String DOC_TYPE="ICS_RECEIVE_BATCH";

    /*单据类型*/
    private String DOC_NO;

    /*单据状态*/
    private String DOC_STATUS;

    /*单据时间*/
    private String DOC_DATE;

    private String GROUP_CODE="";

    private String GROUP_NAME="";

    private String NODE_CODE="";

    private String NODE_NAME="";

    private String COMPANY_CODE="";

    private String COMPANY_NAME="";
    /*系统*/
    private String SOURCE_SYS="ICS";


    public String getDOC_TYPE() {
        return DOC_TYPE;
    }

    public void setDOC_TYPE(String DOC_TYPE) {
        this.DOC_TYPE = DOC_TYPE;
    }

    public String getDOC_NO() {
        return DOC_NO;
    }

    public void setDOC_NO(String DOC_NO) {
        this.DOC_NO = DOC_NO;
    }

    public String getDOC_STATUS() {
        return DOC_STATUS;
    }

    public void setDOC_STATUS(String DOC_STATUS) {
        this.DOC_STATUS = DOC_STATUS;
    }

    public String getDOC_DATE() {
        return DOC_DATE;
    }

    public void setDOC_DATE(String DOC_DATE) {
        this.DOC_DATE = DOC_DATE;
    }

    public String getGROUP_CODE() {
        return GROUP_CODE;
    }

    public void setGROUP_CODE(String GROUP_CODE) {
        this.GROUP_CODE = GROUP_CODE;
    }

    public String getGROUP_NAME() {
        return GROUP_NAME;
    }

    public void setGROUP_NAME(String GROUP_NAME) {
        this.GROUP_NAME = GROUP_NAME;
    }

    public String getNODE_CODE() {
        return NODE_CODE;
    }

    public void setNODE_CODE(String NODE_CODE) {
        this.NODE_CODE = NODE_CODE;
    }

    public String getNODE_NAME() {
        return NODE_NAME;
    }

    public void setNODE_NAME(String NODE_NAME) {
        this.NODE_NAME = NODE_NAME;
    }

    public String getCOMPANY_CODE() {
        return COMPANY_CODE;
    }

    public void setCOMPANY_CODE(String COMPANY_CODE) {
        this.COMPANY_CODE = COMPANY_CODE;
    }

    public String getCOMPANY_NAME() {
        return COMPANY_NAME;
    }

    public void setCOMPANY_NAME(String COMPANY_NAME) {
        this.COMPANY_NAME = COMPANY_NAME;
    }

    public String getSOURCE_SYS() {
        return SOURCE_SYS;
    }

    public void setSOURCE_SYS(String SOURCE_SYS) {
        this.SOURCE_SYS = SOURCE_SYS;
    }

    @Override
    public String toString() {
        return "BMS{" +
                "DOC_TYPE='" + DOC_TYPE + '\'' +
                ", DOC_NO='" + DOC_NO + '\'' +
                ", DOC_STATUS='" + DOC_STATUS + '\'' +
                ", DOC_DATE='" + DOC_DATE + '\'' +
                ", GROUP_CODE='" + GROUP_CODE + '\'' +
                ", GROUP_NAME='" + GROUP_NAME + '\'' +
                ", NODE_CODE='" + NODE_CODE + '\'' +
                ", NODE_NAME='" + NODE_NAME + '\'' +
                ", COMPANY_CODE='" + COMPANY_CODE + '\'' +
                ", COMPANY_NAME='" + COMPANY_NAME + '\'' +
                ", SOURCE_SYS='" + SOURCE_SYS + '\'' +
                '}';
    }
}
