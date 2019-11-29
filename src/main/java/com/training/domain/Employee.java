package com.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//员工表
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    //用户名
    private String userName;
    //密码
    private String password;
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
    private Double remainMoney;
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
    private Double StarLevel;
    //审核状态,-1审核不通过,0未审核,1审核通过
    private int checkStatus;
}
