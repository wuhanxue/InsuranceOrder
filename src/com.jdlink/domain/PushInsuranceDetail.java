package com.jdlink.domain;

import java.util.List;
import java.util.Map;

/*保单信息上传订单*/
public class PushInsuranceDetail {

    private String MESSAGE_ID;

    private String MESSAGE_FORMAT="json";

    private String MESSAGE_TYPE="INSURANCE_DT";

    private String COMPRRESS="0";

    /*发送日期*/
    private String SEND_DATE;

    private String SEND_SYS="ICS";

    private String SENDER="0001";

    private String RECEIVE_SYS="OMS";

    private String RECEIVER="00001";

    private String REMARK="备注";

    private  List<Map<String,List<InsuranceDetail>>> DATA;


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

    public List<Map<String, List<InsuranceDetail>>> getDATA() {
        return DATA;
    }

    public void setDATA(List<Map<String, List<InsuranceDetail>>> DATA) {
        this.DATA = DATA;
    }

    @Override
    public String toString() {
        return "PushInsuranceDetail{" +
                "MESSAGE_ID='" + MESSAGE_ID + '\'' +
                ", MESSAGE_FORMAT='" + MESSAGE_FORMAT + '\'' +
                ", MESSAGE_TYPE='" + MESSAGE_TYPE + '\'' +
                ", COMPRRESS='" + COMPRRESS + '\'' +
                ", SEND_DATE='" + SEND_DATE + '\'' +
                ", SEND_SYS='" + SEND_SYS + '\'' +
                ", SENDER='" + SENDER + '\'' +
                ", RECEIVE_SYS='" + RECEIVE_SYS + '\'' +
                ", RECEIVER='" + RECEIVER + '\'' +
                ", REMARK='" + REMARK + '\'' +
                ", DATA=" + DATA +
                '}';
    }
}
