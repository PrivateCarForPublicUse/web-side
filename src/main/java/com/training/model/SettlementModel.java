package com.training.model;

import com.training.domain.Settlement;

public class SettlementModel {
    private Settlement settlement;
    private RouteModel routeModel;

    public SettlementModel(){}

    public SettlementModel(Settlement settlement, RouteModel route) {
        this.settlement = settlement;
        this.routeModel = route;
    }



    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public RouteModel getRoute() {
        return routeModel;
    }

    public void setRoute(RouteModel route) {
        this.routeModel = route;
    }
}
