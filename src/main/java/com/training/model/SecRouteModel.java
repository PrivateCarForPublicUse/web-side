package com.training.model;

import com.training.domain.SecRoute;
import com.training.domain.Settlement;

public class SecRouteModel {
    private SecRoute secRoute;
    private Settlement settlement;

    public SecRouteModel() {
    }

    public SecRouteModel(SecRoute secRoute, Settlement settlement) {
        this.secRoute = secRoute;
        this.settlement = settlement;
    }

    public SecRoute getSecRoute() {
        return secRoute;
    }

    public void setSecRoute(SecRoute secRoute) {
        this.secRoute = secRoute;
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }
}
