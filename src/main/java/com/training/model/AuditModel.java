package com.training.model;

import com.training.domain.Car;
import com.training.domain.Route;
import com.training.domain.User;

import java.util.List;

//返回当前需要审批的相关信息
public class AuditModel {
    private List<User> users;
    private List<Car> cars;
    private List<Route> routes;
    private List<Route> reimburses;
    public AuditModel(){}

    public AuditModel(List<User> users, List<Car> cars, List<Route> routes, List<Route> reimburses) {
        this.users = users;
        this.cars = cars;
        this.routes = routes;
        this.reimburses = reimburses;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<Route> getReimburses() {
        return reimburses;
    }

    public void setReimburses(List<Route> reimburses) {
        this.reimburses = reimburses;
    }
}
