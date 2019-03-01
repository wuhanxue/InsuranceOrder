package com.jdlink.domain;
/*报文数据结构
* 由 token+CDEC构成
* */
public class Message {

   private String tokenId;

   private CDEC CDEC;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public com.jdlink.domain.CDEC getCDEC() {
        return CDEC;
    }

    public void setCDEC(com.jdlink.domain.CDEC CDEC) {
        this.CDEC = CDEC;
    }
}
