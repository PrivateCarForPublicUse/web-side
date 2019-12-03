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
    private Long EmployeeId;
    //行程表id
    private Long RouteId;
    //段行程id
    private Long SecRouteId;
    //行程实际开始时间
    private String carStartTime;
    //行程实际结束时间
    private String carStopTime;
    //行驶路程（公里）
    private Double drivingDistance;
    //行驶费用（元）
    private Double drivingCost;
    //路径url
    private String routeUrl;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployeeId(Long employeeId) {
        EmployeeId = employeeId;
    }

    public void setRouteId(Long routeId) {
        RouteId = routeId;
    }

    public void setSecRouteId(Long secRouteId) {
        SecRouteId = secRouteId;
    }

    public void setCarStartTime(String carStartTime) {
        this.carStartTime = carStartTime;
    }

    public void setCarStopTime(String carStopTime) {
        this.carStopTime = carStopTime;
    }

    public void setDrivingDistance(Double drivingDistance) {
        this.drivingDistance = drivingDistance;
    }

    public void setDrivingCost(Double drivingCost) {
        this.drivingCost = drivingCost;
    }

    public void setRouteUrl(String routeUrl) {
        this.routeUrl = routeUrl;
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return EmployeeId;
    }

    public Long getRouteId() {
        return RouteId;
    }

    public Long getSecRouteId() {
        return SecRouteId;
    }

    public String getCarStartTime() {
        return carStartTime;
    }

    public String getCarStopTime() {
        return carStopTime;
    }

    public Double getDrivingDistance() {
        return drivingDistance;
    }

    public Double getDrivingCost() {
        return drivingCost;
    }

    public String getRouteUrl() {
        return routeUrl;
    }
}
