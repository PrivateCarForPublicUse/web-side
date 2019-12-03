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
    private Long employeeId;
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
    //行驶费用（元）
    private double drivingCost;
    //路径url
    private String routeUrl;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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
        this.routeUrl = routeUrl;
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
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
        return routeUrl;
    }
}
