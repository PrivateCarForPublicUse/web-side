package com.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//管理员表
@Entity
public class Master {
    @Id
    @GeneratedValue
    private Long id;
    //管理员用户名
    private String name;
    //管理员密码
    private String password;
    //是否为企业管理员，0：不是 1：是
    private int isCompanyMaster;
    //对应的企业id
    private Long companyId;
    //管理员昵称
    private String masterName;

    private Integer accountId;

    public Master() {
    }

    public Master(String name, String password, int isCompanyMaster, Long companyId, String masterName, Integer accountId) {
        this.name = name;
        this.password = password;
        this.isCompanyMaster = isCompanyMaster;
        this.companyId = companyId;
        this.masterName = masterName;
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsCompanyMaster() {
        return isCompanyMaster;
    }

    public void setIsCompanyMaster(int isCompanyMaster) {
        this.isCompanyMaster = isCompanyMaster;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
