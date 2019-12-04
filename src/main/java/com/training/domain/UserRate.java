package com.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class UserRate {
    @Id
    @GeneratedValue
    private Long id;
    //评论时间
    private String time;
    //评论人id
    private Long userId;
    //被评论人id
    private Long  evaluateeId;
    //评语
    private String comment;
    //评分
    private Double rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEvaluateeId() {
        return evaluateeId;
    }

    public void setEvaluateeId(Long evaluateeId) {
        this.evaluateeId = evaluateeId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
