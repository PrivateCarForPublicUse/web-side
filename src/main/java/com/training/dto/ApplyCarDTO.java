package com.training.dto;

import java.util.List;

public class ApplyCarDTO {
    private String startTime;
    private String endTime;
    private String reason;
    private List<String> names;
    private List<PointLatDTO> lats;
    private Long carId;


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<PointLatDTO> getLats() {
        return lats;
    }

    public void setLats(List<PointLatDTO> lats) {
        this.lats = lats;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public ApplyCarDTO(String startTime, String endTime, String reason, List<String> names, List<PointLatDTO> lats, Long carId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.names = names;
        this.lats = lats;
        this.carId = carId;
    }
}
