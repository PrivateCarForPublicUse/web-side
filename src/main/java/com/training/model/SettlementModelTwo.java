package com.training.model;

import java.util.List;

public class SettlementModelTwo {
    private List<SettlementModel> settlementModels;
    private Double totalCost;
    private Double totalDistance;

    public List<SettlementModel> getSettlementModels() {
        return settlementModels;
    }

    public void setSettlementModels(List<SettlementModel> settlementModels) {
        this.settlementModels = settlementModels;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public SettlementModelTwo(List<SettlementModel> settlementModels, Double totalCost, Double totalDistance) {
        this.settlementModels = settlementModels;
        this.totalCost = totalCost;
        this.totalDistance = totalDistance;
    }
}
