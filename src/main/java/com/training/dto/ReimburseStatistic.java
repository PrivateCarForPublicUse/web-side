package com.training.dto;

import java.util.List;

public class ReimburseStatistic {
    List<ReimburseStatisticByDay> dataOfEveryDay;
    //报销总和
    ReimburseDetail sum;

    public List<ReimburseStatisticByDay> getDataOfEveryDay() {
        return dataOfEveryDay;
    }

    public void setDataOfEveryDay(List<ReimburseStatisticByDay> dataOfEveryDay) {
        this.dataOfEveryDay = dataOfEveryDay;
    }

    public ReimburseDetail getSum() {
        return sum;
    }

    public void setSum(ReimburseDetail sum) {
        this.sum = sum;
    }
}
