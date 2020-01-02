package com.training.dto;

import java.util.List;

public class IsReimburseIdsDTO {
    private int[] ids;

    public IsReimburseIdsDTO(){}

    public IsReimburseIdsDTO(int[] ids) {
        this.ids = ids;
    }

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }
}
