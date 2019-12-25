package com.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reimburse {
    @Id
    @GeneratedValue
    //报销id
    private Long id;
    //行程id
    private Long routeId;
    //-1 报销失败；0 未报销；1 已报销；2 审核中
    private int isReimburse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeid) {
        routeId = routeid;
    }

    public int getIsReimburse() {
        return isReimburse;
    }

    public void setIsReimburse(int isReimburse) {
        this.isReimburse = isReimburse;
    }
}
