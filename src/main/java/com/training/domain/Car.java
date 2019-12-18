package com.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue
    //车辆id
    private Long id;
    // 牌照
    private String license;
    //车辆照片url
    private String picture;
    //车辆行驶证url
    private String DrivingLicenseUrl;
    //车辆品牌
    private String band;
    //车辆型号
    private String type;
    //车辆星级
    private double StarOfCar;
    //保险公司
    private String InsuranceCompany;
    //交强险保单
    private String StrongInsurancePolicy;
    //商业险保单
    private String CommercialInsurancePolicy;
    //车主id
    private Long userId;
    //公私状态 （1 公车；0 私车）
    private int isPublic;
    //使用状态（0 空闲；1 审核中；2 使用中 ）
    private int isUse;
    //车辆公用开始时间
    private String starTime;
    //车辆公用结束时间
    private String endTime;
    //车辆排量
    private double displacement;
    //是否删除
    private int isDeleted;



    public Car() {
    }

    public Car(String license, String picture, String drivingLicenseUrl, String band, String type, double starOfCar, String insuranceCompany, String strongInsurancePolicy, String commercialInsurancePolicy, Long carHostId, int isPublic, int isUse, String starTime, String endTime, double displacement) {
        this.license = license;
        this.picture = picture;
        DrivingLicenseUrl = drivingLicenseUrl;
        this.band = band;
        this.type = type;
        StarOfCar = starOfCar;
        InsuranceCompany = insuranceCompany;
        StrongInsurancePolicy = strongInsurancePolicy;
        CommercialInsurancePolicy = commercialInsurancePolicy;
        userId = carHostId;
        this.isPublic = isPublic;
        this.isUse = isUse;
        this.starTime = starTime;
        this.endTime = endTime;
        this.displacement = displacement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDrivingLicenseUrl() {
        return DrivingLicenseUrl;
    }

    public void setDrivingLicenseUrl(String drivingLicenseUrl) {
        DrivingLicenseUrl = drivingLicenseUrl;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getStarOfCar() {
        return StarOfCar;
    }

    public void setStarOfCar(double starOfCar) {
        StarOfCar = starOfCar;
    }

    public String getInsuranceCompany() {
        return InsuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        InsuranceCompany = insuranceCompany;
    }

    public String getStrongInsurancePolicy() {
        return StrongInsurancePolicy;
    }

    public void setStrongInsurancePolicy(String strongInsurancePolicy) {
        StrongInsurancePolicy = strongInsurancePolicy;
    }

    public String getCommercialInsurancePolicy() {
        return CommercialInsurancePolicy;
    }

    public void setCommercialInsurancePolicy(String commercialInsurancePolicy) {
        CommercialInsurancePolicy = commercialInsurancePolicy;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long carHostId) {
        userId = carHostId;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public int getIsUse() {
        return isUse;
    }

    public void setIsUse(int isUse) {
        this.isUse = isUse;
    }

    public String getStarTime() {
        return starTime;
    }

    public void setStarTime(String starTime) {
        this.starTime = starTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(double displacement) {
        this.displacement = displacement;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

}
