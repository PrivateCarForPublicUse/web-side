package com.training.Util;


public class Pair{
    private String time;
    private double num;

    public Pair(){}
    public Pair(String time,double num){
        this.time=time;
        this.num=num;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }
}