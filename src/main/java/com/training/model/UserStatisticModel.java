package com.training.model;

import com.training.Util.Pair;

import java.util.List;

public class UserStatisticModel {
    //总报销次数
    private int totalReimburseNum;
    //总报销金额
    private int totalReimbursement;
    //总取消次数
    private int totalCancelNum;
    //未报销次数
    private List<Pair> unReimbursedNumByDate;
    //已报销金额
    private List<Pair> reimbursedAmountByDate;
    //未审核金额
    private List<Pair> unReimbursedAmountByDate;
    //未通过报销金额
    private List<Pair> failReimburseAmountByDate;
    public UserStatisticModel(){}

    public UserStatisticModel(int totalReimburseNum, int totalReimbursement, int totalCancelNum, List<Pair> unReimbursedNumByDate, List<Pair> reimbursedAmountByDate, List<Pair> unReimbursedAmountByDate, List<Pair> failReimburseAmountByDate) {
        this.totalReimburseNum = totalReimburseNum;
        this.totalReimbursement = totalReimbursement;
        this.totalCancelNum = totalCancelNum;
        this.unReimbursedNumByDate = unReimbursedNumByDate;
        this.reimbursedAmountByDate = reimbursedAmountByDate;
        this.unReimbursedAmountByDate = unReimbursedAmountByDate;
        this.failReimburseAmountByDate = failReimburseAmountByDate;
    }

    public int getTotalReimburseNum() {
        return totalReimburseNum;
    }

    public void setTotalReimburseNum(int totalReimburseNum) {
        this.totalReimburseNum = totalReimburseNum;
    }

    public int getTotalReimbursement() {
        return totalReimbursement;
    }

    public void setTotalReimbursement(int totalReimbursement) {
        this.totalReimbursement = totalReimbursement;
    }

    public int getTotalCancelNum() {
        return totalCancelNum;
    }

    public void setTotalCancelNum(int totalCancelNum) {
        this.totalCancelNum = totalCancelNum;
    }

    public List<Pair> getUnReimbursedNumByDate() {
        return unReimbursedNumByDate;
    }

    public void setUnReimbursedNumByDate(List<Pair> unReimbursedNumByDate) {
        this.unReimbursedNumByDate = unReimbursedNumByDate;
    }

    public List<Pair> getReimbursedAmountByDate() {
        return reimbursedAmountByDate;
    }

    public void setReimbursedAmountByDate(List<Pair> reimbursedAmountByDate) {
        this.reimbursedAmountByDate = reimbursedAmountByDate;
    }

    public List<Pair> getUnReimbursedAmountByDate() {
        return unReimbursedAmountByDate;
    }

    public void setUnReimbursedAmountByDate(List<Pair> unReimbursedAmountByDate) {
        this.unReimbursedAmountByDate = unReimbursedAmountByDate;
    }

    public List<Pair> getFailReimburseAmountByDate() {
        return failReimburseAmountByDate;
    }

    public void setFailReimburseAmountByDate(List<Pair> failReimburseAmountByDate) {
        this.failReimburseAmountByDate = failReimburseAmountByDate;
    }
}
