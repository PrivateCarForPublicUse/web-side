package com.training.model;

import java.util.List;

public class DataModel {
    private Long RoutId;
    private String applyTime;
    private String reason;
    List<SecModel> secModels;
    private Double routelength;
    private Double cost;
    private int isReimburse;

    public DataModel(){}

    public DataModel(Long routId, String applyTime, String reason, List<SecModel> secModels, Double routelength, Double cost, int isReimburse) {
        RoutId = routId;
        this.applyTime = applyTime;
        this.reason = reason;
        this.secModels = secModels;
        this.routelength = routelength;
        this.cost = cost;
        this.isReimburse = isReimburse;
    }

    public Long getRoutId() {
        return RoutId;
    }

    public void setRoutId(Long routId) {
        RoutId = routId;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<SecModel> getSecModels() {
        return secModels;
    }

    public void setSecModels(List<SecModel> secModels) {
        this.secModels = secModels;
    }

    public Double getRoutelength() {
        return routelength;
    }

    public void setRoutelength(Double routelength) {
        this.routelength = routelength;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public int getIsReimburse() {
        return isReimburse;
    }

    public void setIsReimburse(int isReimburse) {
        this.isReimburse = isReimburse;
    }
}
