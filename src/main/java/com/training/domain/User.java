package com.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//员工表
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    //用户名
    private String userName;
    //账号
    private Long accountId;
    //工号
    private Long workNumber;
    //姓名
    private String name;
    //性别，0为男，1为女
    private int gender;
    //手机号
    private String phoneNumber;
    //企业id
    private Long companyId;
    //头像url
    private String headPhotoUrl;
    //账户余额
    private double remainMoney;
    //身份证号
    private String idCardNumber;
    //身份证正面url
    private String idCardFrontUrl;
    //身份证反面url
    private String idCardBackUrl;
    //身份证到期日期
    private String idCardDueDate;
    //驾驶证号
    private String driverLicenseNumber;
    //驾驶证正面url
    private String driverLicenseFrontUrl;
    //驾驶证反面url
    private String driverLicenseBackUrl;
    //驾驶证到期日期
    private String driverLicenseDueDate;
    //驾驶员星级
    private double StarLevel;
    //审核状态,-1审核不通过,0未审核,1审核通过
    private int checkStatus;
    //用户设备id
    private Long tid;
    //是否删除
    private int isDeleted;

    public User(){}

    public User(String userName, Long companyId, String name,int gender,String phoneNumber,Long workNumber){
        this.userName=userName;
        this.companyId=companyId;
        this.name=name;
        this.gender=gender;
        this.phoneNumber=phoneNumber;
        this.workNumber=workNumber;
    }

    public User(String userName, Long accountId, Long workNumber, String name, int gender, String phoneNumber, Long companyId, String headPhotoUrl, String idCardNumber, String idCardFrontUrl, String idCardBackUrl, String driverLicenseNumber, String driverLicenseFrontUrl, String driverLicenseBackUrl, int checkStatus) {
        this.userName = userName;
        this.accountId = accountId;
        this.workNumber = workNumber;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.companyId = companyId;
        this.headPhotoUrl = headPhotoUrl;
        this.idCardNumber = idCardNumber;
        this.idCardFrontUrl = idCardFrontUrl;
        this.idCardBackUrl = idCardBackUrl;
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverLicenseFrontUrl = driverLicenseFrontUrl;
        this.driverLicenseBackUrl = driverLicenseBackUrl;
        this.checkStatus = checkStatus;
    }

    public User(String userName, Long accountId, Long workNumber, String name, int gender, String phoneNumber, Long companyId, String idCardNumber, String driverLicenseNumber, int checkStatus,String[] uuid) {
        this.userName = userName;
        this.accountId = accountId;
        this.workNumber = workNumber;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.companyId = companyId;
        this.headPhotoUrl = uuid[4];//headPhotoUrl;
        this.idCardNumber = idCardNumber;
        this.idCardFrontUrl = uuid[0];//idCardFrontUrl;
        this.idCardBackUrl = uuid[1];//idCardBackUrl;
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverLicenseFrontUrl = uuid[2];//driverLicenseFrontUrl;
        this.driverLicenseBackUrl = uuid[3];//driverLicenseBackUrl;
        this.checkStatus = checkStatus;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Long workNumber) {
        this.workNumber = workNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getHeadPhotoUrl() {
        return headPhotoUrl;
    }

    public void setHeadPhotoUrl(String headPhotoUrl) {
        this.headPhotoUrl = headPhotoUrl;
    }

    public double getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(double remainMoney) {
        this.remainMoney = remainMoney;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getIdCardFrontUrl() {
        return idCardFrontUrl;
    }

    public void setIdCardFrontUrl(String idCardFrontUrl) {
        this.idCardFrontUrl = idCardFrontUrl;
    }

    public String getIdCardBackUrl() {
        return idCardBackUrl;
    }

    public void setIdCardBackUrl(String idCardBackUrl) {
        this.idCardBackUrl = idCardBackUrl;
    }

    public String getIdCardDueDate() {
        return idCardDueDate;
    }

    public void setIdCardDueDate(String idCardDueDate) {
        this.idCardDueDate = idCardDueDate;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getDriverLicenseFrontUrl() {
        return driverLicenseFrontUrl;
    }

    public void setDriverLicenseFrontUrl(String driverLicenseFrontUrl) {
        this.driverLicenseFrontUrl = driverLicenseFrontUrl;
    }

    public String getDriverLicenseBackUrl() {
        return driverLicenseBackUrl;
    }

    public void setDriverLicenseBackUrl(String driverLicenseBackUrl) {
        this.driverLicenseBackUrl = driverLicenseBackUrl;
    }

    public String getDriverLicenseDueDate() {
        return driverLicenseDueDate;
    }

    public void setDriverLicenseDueDate(String driverLicenseDueDate) {
        this.driverLicenseDueDate = driverLicenseDueDate;
    }

    public double getStarLevel() {
        return StarLevel;
    }

    public void setStarLevel(double starLevel) {
        StarLevel = starLevel;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }
}
