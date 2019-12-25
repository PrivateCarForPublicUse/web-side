package com.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SecRoute {
    @Id
    @GeneratedValue
    private Long id;
    //行程表id
    private Long routeId;
    //出发点
    private String origin;
    //目的地
    private String destination;
    //出发地经度
    private String oriLongitude;
    //出发地纬度
    private String oriLatitude;
    //目的地经度
    private String desLongitude;
    //目的地纬度
    private String desLatitude;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
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

    public String getOriLongitude() {
        return oriLongitude;
    }

    public void setOriLongitude(String oriLongitude) {
        this.oriLongitude = oriLongitude;
    }

    public String getOriLatitude() {
        return oriLatitude;
    }

    public void setOriLatitude(String oriLatitude) {
        this.oriLatitude = oriLatitude;
    }

    public String getDesLongitude() {
        return desLongitude;
    }

    public void setDesLongitude(String desLongitude) {
        this.desLongitude = desLongitude;
    }

    public String getDesLatitude() {
        return desLatitude;
    }

    public void setDesLatitude(String desLatitude) {
        this.desLatitude = desLatitude;
    }

    public SecRoute(){}

    public SecRoute(Long routeId,String origin,String destination,String oriLongitude, String oriLatitude, String desLongitude, String desLatitude) {
        this.routeId = routeId;
        this.origin = origin;
        this.destination = destination;
        this.oriLongitude = oriLongitude;
        this.oriLatitude = oriLatitude;
        this.desLongitude = desLongitude;
        this.desLatitude = desLatitude;
    }

}
