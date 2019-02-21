package com.jdlink.domain.dataItem;

import java.util.Date;

/**
 * 数据字典明细
 */
public class DataDictionaryItem {

    /**
     * 明细字段编号
     */
    private String id;

    /**
     * 外键（主表ID）
     */
    private Integer dataDictionaryId;
    /**
     * 明细类型编码
     */
    private String type;
    /**
     * 明细类型名称
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
     * 创建人
     */
    private String creator;
    /**
     * 修改人
     */
    private String modifier;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDataDictionaryId() {
        return dataDictionaryId;
    }

    public void setDataDictionaryId(Integer dataDictionaryId) {
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

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public String toString() {
        return "DataDictionaryItem{" +
                "id=" + id +
                ", dataDictionaryId=" + dataDictionaryId +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", creationTime=" + creationTime +
                ", modifyTime=" + modifyTime +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                '}';
    }
}
