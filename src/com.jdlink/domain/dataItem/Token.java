package com.jdlink.domain.dataItem;
/*Token实体类*/
public class Token {

    private String message;

    private String token;

    private String token_outTime;

    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken_outTime() {
        return token_outTime;
    }

    public void setToken_outTime(String token_outTime) {
        this.token_outTime = token_outTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Token{" +
                "message='" + message + '\'' +
                ", token='" + token + '\'' +
                ", token_outTime='" + token_outTime + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
