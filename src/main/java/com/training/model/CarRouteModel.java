package com.training.model;

import com.training.domain.Car;
import com.training.domain.Route;

import java.util.List;

public class CarRouteModel {
    private Car car;
    private List<RouteModel> routeModels;

    public CarRouteModel(Car car, List<RouteModel> routeModels) {
        this.car = car;
        this.routeModels = routeModels;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<RouteModel> getRouteModels() {
        return routeModels;
    }

    public void setRouteModels(List<RouteModel> routeModels) {
        this.routeModels = routeModels;
    }
}
