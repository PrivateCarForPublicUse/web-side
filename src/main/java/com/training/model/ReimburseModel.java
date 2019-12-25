package com.training.model;

import com.training.domain.*;

import java.util.List;

public class ReimburseModel {
    private Reimburse reimburse;
    private RouteModel routeModel;

    public ReimburseModel(){}

    public ReimburseModel(Reimburse reimburse, RouteModel routeModel) {
        this.reimburse = reimburse;
        this.routeModel = routeModel;
    }

    public Reimburse getReimburse() {
        return reimburse;
    }

    public void setReimburse(Reimburse reimburse) {
        this.reimburse = reimburse;
    }

    public RouteModel getRouteModel() {
        return routeModel;
    }

    public void setRouteModel(RouteModel routeModel) {
        this.routeModel = routeModel;
    }
}
