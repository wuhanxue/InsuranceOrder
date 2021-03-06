package com.jdlink.domain;

import com.jdlink.domain.dataItem.CompanyDataItem;
import com.jdlink.domain.dataItem.DepartmentDataItem;
import com.jdlink.domain.dataItem.TeamDataItem;

import java.util.Date;

/**
 * 用户数据结构
 */
public class User {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户名（账号）
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 公司数据字典
     */
    private CompanyDataItem companyDataItem;
    /**
     * 部门数据字典
     */
    private DepartmentDataItem departmentDataItem;
    /**
     * 项目组数据字典
     */
    private TeamDataItem teamDataItem;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date creationTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 密码修改时间
     */
    private Date passwordModifyTime;
    /**
     * 粗查询关键字
     */
    private String keywords;
    /**
     * 分页
     */
    private Page page;
    /**
     * 记住密码（1记住）
     */
    private Integer remember;

    public Integer getRemember() {
        return remember;
    }

    public void setRemember(Integer remember) {
        this.remember = remember;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyDataItem getCompanyDataItem() {
        return companyDataItem;
    }

    public void setCompanyDataItem(CompanyDataItem companyDataItem) {
        this.companyDataItem = companyDataItem;
    }

    public DepartmentDataItem getDepartmentDataItem() {
        return departmentDataItem;
    }

    public void setDepartmentDataItem(DepartmentDataItem departmentDataItem) {
        this.departmentDataItem = departmentDataItem;
    }

    public TeamDataItem getTeamDataItem() {
        return teamDataItem;
    }

    public void setTeamDataItem(TeamDataItem teamDataItem) {
        this.teamDataItem = teamDataItem;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public Date getPasswordModifyTime() {
        return passwordModifyTime;
    }

    public void setPasswordModifyTime(Date passwordModifyTime) {
        this.passwordModifyTime = passwordModifyTime;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", companyDataItem=" + companyDataItem +
                ", departmentDataItem=" + departmentDataItem +
                ", teamDataItem=" + teamDataItem +
                ", creator='" + creator + '\'' +
                ", creationTime=" + creationTime +
                ", modifyTime=" + modifyTime +
                ", passwordModifyTime=" + passwordModifyTime +
                ", keywords='" + keywords + '\'' +
                ", page=" + page +
                '}';
    }
}
