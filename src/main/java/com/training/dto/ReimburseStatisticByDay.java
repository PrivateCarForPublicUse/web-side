package com.training.dto;

import java.util.Date;
import java.util.List;

public class ReimburseStatisticByDay {
    //时间 格式为yyyy-mm-dd
    String date;
    //通过审核
    ReimburseDetail imbursed;
    //正在审核
    ReimburseDetail isimbursing;
    //拒绝的审核
    ReimburseDetail rejected;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ReimburseDetail getImbursed() {
        return imbursed;
    }

    public void setImbursed(ReimburseDetail imbursed) {
        this.imbursed = imbursed;
    }

    public ReimburseDetail getIsimbursing() {
        return isimbursing;
    }

    public void setIsimbursing(ReimburseDetail isimbursing) {
        this.isimbursing = isimbursing;
    }

    public ReimburseDetail getRejected() {
        return rejected;
    }

    public void setRejected(ReimburseDetail rejected) {
        this.rejected = rejected;
    }
}
