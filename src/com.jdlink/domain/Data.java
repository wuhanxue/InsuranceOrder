package com.jdlink.domain;
/*第三方传来的报文*/
public class Data {

    /*主键*/
    private int id;

    /*内容*/
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
