package com.training.dto;


public class ReviewReimburseDTO {
    private Long routeId;
    private String message;

    public ReviewReimburseDTO(Long routeId, String message) {
        this.routeId = routeId;
        this.message = message;
    }
    public ReviewReimburseDTO(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }
// private Integer status;
}
