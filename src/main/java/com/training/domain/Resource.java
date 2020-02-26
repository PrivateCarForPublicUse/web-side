package com.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Resource {
    @Id
    @GeneratedValue
    private Long id;
    //对应的随机编号
    private String num;
    //对应的后缀名
    private String suffix;

    public Resource(String num, String suffix) {
        this.num = num;
        this.suffix = suffix;
    }

    public Resource(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
