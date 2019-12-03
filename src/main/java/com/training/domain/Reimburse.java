package com.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reimburse {
    @Id
    @GeneratedValue
    private Long id;
    //报销id
    private Long routeId;
    //行程id
    private int isReimburse;
    //-1 报销失败；0 未报销；1 已报销

    public Reimburse(Long id, Long routeid, int isReimburse) {
        this.id = id;
        routeId = routeid;
        this.isReimburse = isReimburse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRouteid() {
        return routeId;
    }

    public void setRouteid(Long routeid) {
        routeId = routeid;
    }

    public int getIsReimburse() {
        return isReimburse;
    }

    public void setIsReimburse(int isReimburse) {
        this.isReimburse = isReimburse;
    }
}
