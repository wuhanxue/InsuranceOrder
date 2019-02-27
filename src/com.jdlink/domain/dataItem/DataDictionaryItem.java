package com.jdlink.domain.dataItem;

import com.jdlink.domain.Page;

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
    private String code;
    /**
     * 明细类型名称
     */
    private String name;
    /**
     * 父类编号（部分涉及）
     */
    private String parentId;
    /**
     * 旧ID（修改ID）
     */
    private String oldId;
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
    /**
     * 分页
     */
    private Page page;
    /**
     * 粗查询
     */
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOldId() {
        return oldId;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "DataDictionaryItem{" +
                "id='" + id + '\'' +
                ", dataDictionaryId=" + dataDictionaryId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", oldId='" + oldId + '\'' +
                ", creationTime=" + creationTime +
                ", modifyTime=" + modifyTime +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                '}';
    }
}
