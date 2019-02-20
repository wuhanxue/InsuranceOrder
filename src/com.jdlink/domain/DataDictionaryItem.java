package com.jdlink.domain;

/**
 * 数据字典明细
 */
public class DataDictionaryItem {

    /**
     * 明细字段编号
     */
    private int id;

    /**
     * 外键（主表ID）
     */
    private int dataDictionaryId;
    /**
     * 明细类型编码
     */
    private String type;
    /**
     * 明细类型名称
     */
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDataDictionaryId() {
        return dataDictionaryId;
    }

    public void setDataDictionaryId(int dataDictionaryId) {
        this.dataDictionaryId = dataDictionaryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DataDictionaryItem{" +
                "id=" + id +
                ", dataDictionaryId=" + dataDictionaryId +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
