package com.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Route {
    @Id
    @GeneratedValue
    private Long id;
    //申请开始时间
    private String applyStartTime;
    //申请结束时间
    private String applyEndTime;
    //申请车辆id
    private Long carId;
    //申请员工id
    private Long userId;
    //申请状态 （-1 审核不通过；0 未审核；1 审核通过；2 行程中；3 已完成；4 已取消）
    private int status;
    //申请理由
    private String reason;
    //-1 报销失败；0 未报销；1 已报销；2 审核中
    private int isReimburse;
    //申请时间
    private String applyTime;
    //管理员处理报销申请时间
    private String handleTime;

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplyStartTime() {
        return applyStartTime;
    }

    public void setApplyStartTime(String applyStartTime) {
        this.applyStartTime = applyStartTime;
    }

    public String getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(String applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getIsReimburse() {
        return isReimburse;
    }

    public void setIsReimburse(int isReimburse) {
        this.isReimburse = isReimburse;
    }

    public Route(){}

    public Route(String applyStartTime, String applyEndTime, Long carId, Long userId, int status, String reason, int isReimburse, String applyTime) {
        this.applyStartTime = applyStartTime;
        this.applyEndTime = applyEndTime;
        this.carId = carId;
        this.userId = userId;
        this.status = status;
        this.reason = reason;
        this.isReimburse = isReimburse;
        this.applyTime = applyTime;
    }
}
