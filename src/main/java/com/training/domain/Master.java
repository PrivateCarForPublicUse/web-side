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
    //是否为企业管理员，0：不是 1：是
    private int isCompanyMaster;
    //对应的企业id
    private Long companyId;
    //管理员昵称
    private String masterName;

    private Long accountId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Master(){}

    public Master(String name, String password, int isComMaster, Long companyId,String masterName) {
        this.name = name;
        this.isCompanyMaster = isComMaster;
        this.companyId = companyId;
        this.masterName = masterName;
    }

    public void setIsCompanyMaster(int isCompanyMaster) {
        this.isCompanyMaster = isCompanyMaster;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
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


    public int getIsCompanyMaster() {
        return isCompanyMaster;
    }

    public void setIsComMaster(int isComMaster) {
        this.isCompanyMaster = isComMaster;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
