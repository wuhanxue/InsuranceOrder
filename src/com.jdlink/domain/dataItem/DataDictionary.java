package com.jdlink.domain.dataItem;

import com.jdlink.domain.Page;

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
    private Integer id;
    /**
     * 字典类型编码
     */
    private String code;
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
     * 创建人
     */
    private String creator;
    /**
     * 修改人
     */
    private String modifier;

    /**
     * 字典明细列表
     */
    private List<DataDictionaryItem> dataDictionaryItemList=new ArrayList<>();
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<DataDictionaryItem> getDataDictionaryItemList() {
        return dataDictionaryItemList;
    }

    public void setDataDictionaryItemList(List<DataDictionaryItem> dataDictionaryItemList) {
        this.dataDictionaryItemList = dataDictionaryItemList;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "DataDictionary{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", creationTime=" + creationTime +
                ", modifyTime=" + modifyTime +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", dataDictionaryItemList=" + dataDictionaryItemList +
                '}';
    }
}
