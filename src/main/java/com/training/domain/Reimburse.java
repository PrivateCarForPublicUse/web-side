package com.training.domain;

@Entity
public class Reimburse {
    @Id
    @GeneratedValue
    private Long id;
    //报销id
    private Long Routeid;
    //行程id
    private int isReimburse;
    //-1 报销失败；0 未报销；1 已报销

    public Reimburse(Long id, Long routeid, int isReimburse) {
        this.id = id;
        Routeid = routeid;
        this.isReimburse = isReimburse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRouteid() {
        return Routeid;
    }

    public void setRouteid(Long routeid) {
        Routeid = routeid;
    }

    public int getIsReimburse() {
        return isReimburse;
    }

    public void setIsReimburse(int isReimburse) {
        this.isReimburse = isReimburse;
    }
}
