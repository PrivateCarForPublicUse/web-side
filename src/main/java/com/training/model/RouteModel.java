package com.training.model;

import com.training.domain.Car;
import com.training.domain.Route;
import com.training.domain.SecRoute;
import com.training.domain.User;

import java.util.List;

public class RouteModel {
    private User user;
    private Car car;
    private Route route;
    private List<SecRouteModel>secRoutesModel;

    public RouteModel() {}

    public RouteModel(Route route, List<SecRouteModel> secRoutes) {
        this.route = route;
        this.secRoutesModel = secRoutes;
    }

    public RouteModel(User user, Car car, Route route, List<SecRouteModel> secRoutes) {
        this.user = user;
        this.car = car;
        this.route = route;
        this.secRoutesModel = secRoutes;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<SecRouteModel> getSecRoutesModel() {
        return secRoutesModel;
    }

    public void setSecRoutesModel(List<SecRouteModel> secRoutesModel) {
        this.secRoutesModel = secRoutesModel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
