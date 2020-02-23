package com.training.dto;

public class ReimburseDetail {
    //这一天对应多少钱
    double money;
    //这一天对应多少次（报销、通过、审核）
    int time;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
