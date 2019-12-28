package com.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Settlement {
    @Id
    @GeneratedValue
    private Long id;
    //用户id
    private Long userId;
    //行程表id
    private Long routeId;
    //段行程id
    private Long secRouteId;
    //行程实际开始时间
    private String carStartTime;
    //行程实际结束时间
    private String carStopTime;
    //行驶路程（公里）
    private double drivingDistance;
    //计划行驶里程（公里）
    private double plannedDistance;
    //行驶费用（元）
    private double drivingCost;
    //路径的traceId
    private String trid;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTrid() {
        return trid;
    }

    public void setTrid(String trid) {
        this.trid = trid;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public void setSecRouteId(Long secRouteId) {
        this.secRouteId = secRouteId;
    }

    public void setCarStartTime(String carStartTime) {
        this.carStartTime = carStartTime;
    }

    public void setCarStopTime(String carStopTime) {
        this.carStopTime = carStopTime;
    }

    public void setDrivingDistance(double drivingDistance) {
        this.drivingDistance = drivingDistance;
    }

    public void setDrivingCost(double drivingCost) {
        this.drivingCost = drivingCost;
    }

    public void setRouteUrl(String routeUrl) {
        this.trid = routeUrl;
    }

    public Long getId() {
        return id;
    }

    public Long getRouteId() {
        return routeId;
    }

    public Long getSecRouteId() {
        return secRouteId;
    }

    public String getCarStartTime() {
        return carStartTime;
    }

    public String getCarStopTime() {
        return carStopTime;
    }

    public double getDrivingDistance() {
        return drivingDistance;
    }

    public double getDrivingCost() {
        return drivingCost;
    }

    public String getRouteUrl() {
        return trid;
    }

    public double getPlannedDistance() {
        return plannedDistance;
    }

    public void setPlannedDistance(double plannedDistance) {
        this.plannedDistance = plannedDistance;
    }

    public Settlement() { }

    public Settlement(Long userId, Long routeId, Long secRouteId, String carStartTime, String carStopTime, double drivingDistance, double drivingCost, String trid) {
        this.userId = userId;
        this.routeId = routeId;
        this.secRouteId = secRouteId;
        this.carStartTime = carStartTime;
        this.carStopTime = carStopTime;
        this.drivingCost = drivingCost;
        this.drivingDistance = drivingDistance;
        this.trid = trid;
    }
}
