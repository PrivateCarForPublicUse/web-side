package com.training.model;

public class SecModel {
    private Long SecRouteId;
    private String origin;
    private String destination;
    private String carStartTime;
    private String carStopTime;
    private Double drivingDistance;
    private Double drivingCost;

    public SecModel(){}

    public SecModel(Long secRouteId, String origin, String destination, String carStartTime, String carStopTime, Double drivingDistance, Double drivingCost) {
        SecRouteId = secRouteId;
        this.origin = origin;
        this.destination = destination;
        this.carStartTime = carStartTime;
        this.carStopTime = carStopTime;
        this.drivingDistance = drivingDistance;
        this.drivingCost = drivingCost;
    }

    public Long getSecRouteId() {
        return SecRouteId;
    }

    public void setSecRouteId(Long secRouteId) {
        SecRouteId = secRouteId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCarStartTime() {
        return carStartTime;
    }

    public void setCarStartTime(String carStartTime) {
        this.carStartTime = carStartTime;
    }

    public String getCarStopTime() {
        return carStopTime;
    }

    public void setCarStopTime(String carStopTime) {
        this.carStopTime = carStopTime;
    }

    public Double getDrivingDistance() {
        return drivingDistance;
    }

    public void setDrivingDistance(Double drivingDistance) {
        this.drivingDistance = drivingDistance;
    }

    public Double getDrivingCost() {
        return drivingCost;
    }

    public void setDrivingCost(Double drivingCost) {
        this.drivingCost = drivingCost;
    }
}
