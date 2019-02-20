package com.jdlink.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据字典
 */
public class DataDictionary {

    /**
     * 主键
     */
    private int id;
    /**
     * 字典类型编码
     */
    private String type;
    /**
     * 字典类型名称
     */
    private String name;
    /**
     * 创建时间
     */
    private Date creationTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 字典明细列表
     */
    private List<DataDictionaryItem> dataDictionaryItemList=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public List<DataDictionaryItem> getDataDictionaryItemList() {
        return dataDictionaryItemList;
    }

    public void setDataDictionaryItemList(List<DataDictionaryItem> dataDictionaryItemList) {
        this.dataDictionaryItemList = dataDictionaryItemList;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "DataDictionary{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", creationTime=" + creationTime +
                ", modifyTime=" + modifyTime +
                ", dataDictionaryItemList=" + dataDictionaryItemList +
                '}';
    }
}
