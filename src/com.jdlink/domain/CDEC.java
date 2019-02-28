package com.jdlink.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

/*发送给云平台的数据结构*/
public class CDEC {

    /*信息Id*/
    private String MESSAGE_ID;

    /*信息格式*/
    private String MESSAGE_FORMAT="json";

    /*信息类型*/
    private String MESSAGE_TYPE="SYN_OperationTracking";

    /*是否压缩*/
    private String COMPRRESS="0";

    /*发送时间*/
    private String SEND_DATE;

    /*发送系统*/
    private String SEND_SYS="ICS";

    /*发送企业代码*/
    private String SENDER;

    /*接收系统*/
    private String RECEIVE_SYS="CDEC";

    /*接收方*/
    private String RECEIVER="CDEC";

    /*备注*/
    private String REMARK="订单追踪";

    private  Map<String,List<TRACKING>> DATA;


    public String getMESSAGE_ID() {
        return MESSAGE_ID;
    }

    public void setMESSAGE_ID(String MESSAGE_ID) {
        this.MESSAGE_ID = MESSAGE_ID;
    }

    public String getMESSAGE_FORMAT() {
        return MESSAGE_FORMAT;
    }

    public void setMESSAGE_FORMAT(String MESSAGE_FORMAT) {
        this.MESSAGE_FORMAT = MESSAGE_FORMAT;
    }

    public String getMESSAGE_TYPE() {
        return MESSAGE_TYPE;
    }

    public void setMESSAGE_TYPE(String MESSAGE_TYPE) {
        this.MESSAGE_TYPE = MESSAGE_TYPE;
    }

    public String getCOMPRRESS() {
        return COMPRRESS;
    }

    public void setCOMPRRESS(String COMPRRESS) {
        this.COMPRRESS = COMPRRESS;
    }

    public String getSEND_DATE() {
        return SEND_DATE;
    }

    public void setSEND_DATE(String SEND_DATE) {
        this.SEND_DATE = SEND_DATE;
    }

    public String getSEND_SYS() {
        return SEND_SYS;
    }

    public void setSEND_SYS(String SEND_SYS) {
        this.SEND_SYS = SEND_SYS;
    }

    public String getSENDER() {
        return SENDER;
    }

    public void setSENDER(String SENDER) {
        this.SENDER = SENDER;
    }

    public String getRECEIVE_SYS() {
        return RECEIVE_SYS;
    }

    public void setRECEIVE_SYS(String RECEIVE_SYS) {
        this.RECEIVE_SYS = RECEIVE_SYS;
    }

    public String getRECEIVER() {
        return RECEIVER;
    }

    public void setRECEIVER(String RECEIVER) {
        this.RECEIVER = RECEIVER;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public Map<String, List<TRACKING>> getDATA() {
        return DATA;
    }

    public void setDATA(Map<String, List<TRACKING>> DATA) {
        this.DATA = DATA;
    }
}
